package org.talgat.shakirov

import com.jcraft.jsch.JSchException
import fr.janalyse.ssh.{SSH, SSHIdentity}

import java.io.File
import java.nio.file.{Files, Paths}

object SSH extends App {
  val host = "test.com"
  val username = "username"
  val password = "password"
  val sshKeyFile = None
  val fileName = "020518_20220510_Test.txt"
  val directory = Some("directory")
  val bytes = Files.readAllBytes(Paths.get("020518_20220510_Test.txt"))

 def run() =  sendFile(host, username, password, sshKeyFile, bytes, fileName, directory)

  def sendFile(host: String,
               username: String,
               password: String,
               sshKeyFile: Option[File],
               bytes: Array[Byte],
               fileName: String,
               directory: Option[String]): Unit = {

    val sshIdentities = sshKeyFile.map(keyFile => List(SSHIdentity(keyFile.getAbsolutePath))).getOrElse(List.empty)
    val options = jassh.SSHOptions(host = host,
      username = username,
      password = password,
      identities = sshIdentities,
      port = 22
    )

    val ssh = jassh.SSH(options)
    sendFileBySSH(ssh, bytes, fileName, directory)
  }

  def sendFileBySSH(ssh: SSH,
                    bytes: Array[Byte],
                    fileName: String,
                    directory: Option[String] = None): Unit = {
      val shell = ssh.newSftp
      val directoryString = directory.getOrElse("")
      try {
        shell.cd(directoryString)
        shell.putBytes(bytes, s"$fileName")
      } catch {
        case edc: JSchException => println(edc.getMessage)
        case ex: Exception =>
          println(ex.getMessage)
      } finally {
        ssh.close()
      }
  }

}
