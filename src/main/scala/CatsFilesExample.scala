package org.talgat.shakirov

import cats.effect.{IO, Resource}

import java.io.{File, FileInputStream, FileOutputStream, InputStream, OutputStream}
import cats.syntax.all._

object CatsFilesExample {
  //program to guide students from Big Data School of Beeline
  //Show work of IO in cats effect FrameWork

  private def transfer(origin: InputStream, destination: OutputStream): IO[Array[Byte]] =
    for {
      buffer <- IO(new Array[Byte](1024 * 10)) // Allocated only when the IO is evaluated
    } yield buffer

  def copy(origin: File, destination: File): IO[Array[Byte]] = { //copy file from on place to another
    val inIO: IO[InputStream] = IO(new FileInputStream(origin)) //setting IO
    val outIO: IO[OutputStream] = IO(new FileOutputStream(destination)) //setting IO

    (inIO, outIO) // Getting resources
      .tupled // From (IO[InputStream], IO[OutputStream]) to IO[(InputStream, OutputStream)] converting import cats.syntax.all.tuple2
      .bracket {
        case (in, out) =>
          transfer(in, out) // Using resources (for copying data, in this case)
      } {
        case (in, out) => // Freeing resources
          (IO(in.close()), IO(out.close()))
            .tupled // From (IO[Unit], IO[Unit]) to IO[(Unit, Unit)] same tuple
            .handleErrorWith(_ => IO.unit).void
      }
  }

  private def inputStream(f: File): Resource[IO, FileInputStream] =
    Resource.make {
      IO(new FileInputStream(f)) // build
    } { inStream =>
      IO(inStream.close()).handleErrorWith(_ => IO.unit) // release
    }

  private def outputStream(f: File): Resource[IO, FileOutputStream] =
    Resource.make {
      IO(new FileOutputStream(f)) // build
    } { outStream =>
      IO(outStream.close()).handleErrorWith(_ => IO.unit) // release
    }

  def inputOutputStreams(in: File, out: File): Resource[IO, (InputStream, OutputStream)] =
    for {
      inStream <- inputStream(in)
      outStream <- outputStream(out)
    } yield (inStream, outStream)
}
