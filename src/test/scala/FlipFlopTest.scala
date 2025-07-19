import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class DFlipFlopTest extends AnyFlatSpec with ChiselScalatestTester {
  
  "DFlipFlop" should "work correctly" in {
    test(new DFlipFlop).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      
      // Initial state check
      dut.io.reset.poke(true.B)
      dut.clock.step(1)
      dut.io.q.expect(false.B)
      dut.io.qn.expect(true.B)
      
      // Release reset
      dut.io.reset.poke(false.B)
      
      // Test when D=0
      dut.io.d.poke(false.B)
      dut.clock.step(1)
      dut.io.q.expect(false.B)
      dut.io.qn.expect(true.B)
      
      // Test when D=1
      dut.io.d.poke(true.B)
      dut.clock.step(1)
      dut.io.q.expect(true.B)
      dut.io.qn.expect(false.B)
      
      // Change D back to 0
      dut.io.d.poke(false.B)
      dut.clock.step(1)
      dut.io.q.expect(false.B)
      dut.io.qn.expect(true.B)
      
      // Multiple clock cycles with changing D input
      for (i <- 0 until 8) {
        val data = (i % 2) == 1
        dut.io.d.poke(data.B)
        dut.clock.step(1)
        dut.io.q.expect(data.B)
        dut.io.qn.expect((!data).B)
      }
      
      // Reset test
      dut.io.d.poke(true.B)
      dut.clock.step(1)
      dut.io.q.expect(true.B)
      
      dut.io.reset.poke(true.B)
      dut.clock.step(1)
      dut.io.q.expect(false.B)
      dut.io.qn.expect(true.B)
    }
  }
}
