package alerman.advent.day16;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day16Part1 {
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("/home/alerman/IdeaProjects/adventofcode18/daysAfter13/src/main/resources/day16part1-input.txt"));
        boolean inOp = true;
        String before = "";
        String op = "";
        String after = "";
        Map<String,Integer> uniqueCount = new HashMap<>();
        Map<String,Set<OPCODE>> possibilities = new HashMap<>();
        for(String line : lines)
        {
            if(line.length()>0) {
                if (line.contains("Before")) {
                    inOp = true;
                    before = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                } else {
                    if (line.contains("After")) {
                        inOp = false;
                        after = line.substring(line.indexOf("[") + 1, line.indexOf("]"));

                        int[] beforeInt = Arrays.stream(before.split(",")).mapToInt(value -> Integer.parseInt(value.trim())).toArray();
                        int[] afterInt = Arrays.stream(after.split(",")).mapToInt(value -> Integer.parseInt(value.trim())).toArray();
                        int[] opInt = Arrays.stream(op.split(" ")).mapToInt(value -> Integer.parseInt(value.trim())).toArray();
                        for (OPCODE opcode : OPCODE.values()) {
                            int[] result = applyOpcode(opcode, beforeInt, opInt);
                            if (result[0] == afterInt[0] &&
                                    result[1] == afterInt[1] &&
                                    result[2] == afterInt[2] &&
                                    result[3] == afterInt[3]) {
                                if (possibilities.get(op) == null) {
                                    possibilities.put(op, new HashSet<>());
                                    if(uniqueCount.get(op) == null)
                                    {
                                        uniqueCount.put(op, 1);
                                    }
                                }
                                possibilities.get(op).add(opcode);
                                uniqueCount.put(op, uniqueCount.get(op)+1);
                            }
                            beforeInt = Arrays.stream(before.split(",")).mapToInt(value -> Integer.parseInt(value.trim())).toArray();
                        }

                    } else {
                        if (inOp) ;
                        {
                            op = line;
                        }
                    }
                }
            }

        }

        int i = 0;
        for(String input : possibilities.keySet())
        {
            if(possibilities.get(input).size()>=3)
            {
                i+=uniqueCount.get(input);
            }
        }

        System.out.println("Num with at least 3: " + i);
    }

    private static int[] applyOpcode(OPCODE opcode, int[] beforeInt, int[] op) {
        switch (opcode)
        {
            case ADDR:
                int regA = op[1];
                int regB = op[2];
                int regC = op[3];
                int valA = beforeInt[regA];
                int valB = beforeInt[regB];
                beforeInt[regC] = valA + valB;
                return beforeInt;
                
            case ADDI:
                 regA = op[1];
                 regC = op[3];
                 valA = beforeInt[regA];
                 valB = op[2];
                beforeInt[regC] = valA + valB;
                return beforeInt;
                
            case MULR:
                 regA = op[1];
                 regB = op[2];
                 regC = op[3];
                 valA = beforeInt[regA];
                 valB = beforeInt[regB];
                beforeInt[regC] = valA * valB;
                return beforeInt;
                
            case MULI:
                regA = op[1];
                regC = op[3];
                valA = beforeInt[regA];
                valB = op[2];
                beforeInt[regC] = valA * valB;
                return beforeInt;
                
            case BANR:
                regA = op[1];
                regB = op[2];
                regC = op[3];
                valA = beforeInt[regA];
                valB = beforeInt[regB];
                beforeInt[regC] = valA & valB;
                return beforeInt;
                
            case BANI:
                regA = op[1];
                regC = op[3];
                valA = beforeInt[regA];
                valB = op[2];
                beforeInt[regC] = valA & valB;
                return beforeInt;
                
            case BORR:
                regA = op[1];
                regC = op[3];
                valA = beforeInt[regA];
                valB = op[2];
                beforeInt[regC] = valA | valB;
                return beforeInt;
                
            case BORI:
                regA = op[1];
                regC = op[3];
                valA = beforeInt[regA];
                valB = op[2];
                beforeInt[regC] = valA | valB;
                return beforeInt;
                
            case SETR:
                regA = op[1];
                regC = op[3];
                valA = beforeInt[regA];
                beforeInt[regC] = valA;
                return beforeInt;
                
            case SETI:
                valA = op[1];
                regC = op[3];
                beforeInt[regC] = valA;
                return beforeInt;
                
            case GTRR:
                regA = op[1];
                regB = op[2];
                regC = op[3];
                valA = beforeInt[regA];
                valB = beforeInt[regB];
                beforeInt[regC] = valA>valB ? 1 : 0;
                return beforeInt;
                
            case GTIR:
                regB = op[2];
                regC = op[3];
                valA = op[1];
                valB = beforeInt[regB];
                beforeInt[regC] = valA>valB ? 1 : 0;
                return beforeInt;
                
            case GTRI:
                regA = op[1];
                regC = op[3];
                valB = op[2];
                valA = beforeInt[regA];
                beforeInt[regC] = valA>valB ? 1 : 0;
                return beforeInt;
                
            case EQRR:
                regA = op[1];
                regB = op[2];
                regC = op[3];
                valA = beforeInt[regA];
                valB = beforeInt[regB];
                beforeInt[regC] = valA == valB ? 1 : 0;
                return beforeInt;
            
            case EQIR:
                regB = op[2];
                regC = op[3];
                valA = op[1];
                valB = beforeInt[regB];
                beforeInt[regC] = valA == valB ? 1 : 0;
                return beforeInt;
            
            case EQRI:
                regA = op[1];
                regC = op[3];
                valB = op[2];
                valA = beforeInt[regA];
                beforeInt[regC] = valA == valB ? 1 : 0;
                return beforeInt;
            
        }
        return null;

    }
}
