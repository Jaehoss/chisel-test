FIRRTL version 1.2.0
circuit DFlipFlop :
  module DFlipFlop :
    input clock : Clock
    input reset : UInt<1>
    input io_d : UInt<1> @[src/main/scala/FlipFlop.scala 6:14]
    input io_reset : UInt<1> @[src/main/scala/FlipFlop.scala 6:14]
    output io_q : UInt<1> @[src/main/scala/FlipFlop.scala 6:14]
    output io_qn : UInt<1> @[src/main/scala/FlipFlop.scala 6:14]

    reg reg : UInt<1>, clock with :
      reset => (UInt<1>("h0"), reg) @[src/main/scala/FlipFlop.scala 14:20]
    node _GEN_0 = mux(io_reset, UInt<1>("h0"), io_d) @[src/main/scala/FlipFlop.scala 17:18 18:9 20:9]
    node _io_qn_T = not(reg) @[src/main/scala/FlipFlop.scala 25:12]
    io_q <= reg @[src/main/scala/FlipFlop.scala 24:8]
    io_qn <= _io_qn_T @[src/main/scala/FlipFlop.scala 25:9]
    reg <= mux(reset, UInt<1>("h0"), _GEN_0) @[src/main/scala/FlipFlop.scala 14:{20,20}]
