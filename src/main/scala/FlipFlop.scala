import chisel3._
import chisel3.util._

// D Flip-Flop Module
class DFlipFlop extends Module {
  val io = IO(new Bundle {
    val d = Input(Bool())      // Data input
    val reset = Input(Bool())  // Reset input
    val q = Output(Bool())     // Output
    val qn = Output(Bool())    // Inverted output
  })
  
  // Internal register
  val reg = RegInit(false.B)
  
  // Clock positive edge operation
  when(io.reset) {
    reg := false.B
  }.otherwise {
    reg := io.d
  }
  
  // Output connections
  io.q := reg
  io.qn := ~reg
}

// JK Flip-Flop Module
class JKFlipFlop extends Module {
  val io = IO(new Bundle {
    val j = Input(Bool())
    val k = Input(Bool())
    val reset = Input(Bool())
    val q = Output(Bool())
    val qn = Output(Bool())
  })
  
  val reg = RegInit(false.B)
  
  when(io.reset) {
    reg := false.B
  }.otherwise {
    when(io.j && io.k) {
      reg := ~reg  // Toggle
    }.elsewhen(io.j && !io.k) {
      reg := true.B  // Set
    }.elsewhen(!io.j && io.k) {
      reg := false.B  // Reset
    }
    // (!j && !k) -> Hold state (no change)
  }
  
  io.q := reg
  io.qn := ~reg
}

// T Flip-Flop Module
class TFlipFlop extends Module {
  val io = IO(new Bundle {
    val t = Input(Bool())
    val reset = Input(Bool())
    val q = Output(Bool())
    val qn = Output(Bool())
  })
  
  val reg = RegInit(false.B)
  
  when(io.reset) {
    reg := false.B
  }.elsewhen(io.t) {
    reg := ~reg  // Toggle when T is high
  }
  // When T is low, hold the current state
  
  io.q := reg
  io.qn := ~reg
}
