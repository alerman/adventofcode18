package alerman.advent.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static alerman.advent.day16.OPCODE.*;

public class Day16Part2 {
    public static void main(String[] args) throws IOException {
        OPCODE[] opcodes = new OPCODE[16];
        opcodes[0] = SETR;
        opcodes[1] = EQRR;
        opcodes[2] = GTRI;
        opcodes[3] = MULI;
        opcodes[4] = EQIR;
        opcodes[5] = BORR;
        opcodes[6] = BORI;
        opcodes[7] = MULR;
        opcodes[8] = GTRR;
        opcodes[9] = SETI;
        opcodes[10] = BANR;
        opcodes[11] = EQRI;
        opcodes[12] = ADDR;
        opcodes[13] = GTIR;
        opcodes[14] = ADDI;
        opcodes[15] = BANI;

        List<String> lines = Files.readAllLines(Paths.get("/home/alerman/IdeaProjects/adventofcode18/daysAfter13/src/main/resources/day16part2-input.txt"));

        int[] registers = new int[4];
        registers[0] = 0;
        registers[1] = 0;
        registers[2] = 0;
        registers[3] = 0;
        for(String line : lines)
        {
            int[] lineArr = Arrays.stream(line.split(" ")).mapToInt(value -> Integer.parseInt(value)).toArray();
            registers = Day16Part1.applyOpcode(opcodes[lineArr[0]],registers, lineArr);
        }

        //460 was too low. As was 466.
        System.out.println(registers[0]);
    }
}
