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
        Map<String, Set<OPCODE>> possibilities = new HashMap<>();
        for (String line : lines) {
            if (line.length() > 0) {
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
                            String key = before + " " + op + " " + after;
                            if (result[0] == afterInt[0] &&
                                    result[1] == afterInt[1] &&
                                    result[2] == afterInt[2] &&
                                    result[3] == afterInt[3]) {
                                possibilities.computeIfAbsent(key, k -> new TreeSet<>());
                                possibilities.get(key).add(opcode);
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
        int j=0;
        for(String input : possibilities.keySet())
        {
            if(possibilities.get(input).size()>=3)
            {
                j++;
            }
        }
        System.out.println(j);

        Map<Integer,OPCODE> known = new TreeMap<>();
        int i = 0;
        while(known.size()<16) {
            for (String input : possibilities.keySet()) {
                Set<OPCODE> codes = possibilities.get(input);
                codes.removeAll(known.values());
                if (codes.size() >= 3) {
                    i++;
                }
                if (codes.size() == 1) {
                    known.put(Integer.parseInt(input.split(" ")[4]), codes.iterator().next());
                }
            }

            for(Integer integ : known.keySet())
            {
                System.out.println("opcodes[" + integ + "] = " + known.get(integ) +";");
            }
       }

        //Answer for part 1 was 509
            System.out.println("Num with at least 3: " +  i);

    }

    public static int[] applyOpcode(OPCODE opcode, int[] beforeInt, int[] op) {

        int regC = op[3];
        switch (opcode)
        {
            case ADDR:
                int regA = op[1];
                int valA = beforeInt[regA];
                int regB = op[2];
                int valB = beforeInt[regB];
                beforeInt[regC] = valA + valB;
                return beforeInt;
                
            case ADDI:
                 regA = op[1];
                 valA = beforeInt[regA];
                 valB = op[2];
                beforeInt[regC] = valA + valB;
                return beforeInt;
                
            case MULR:
                 regA = op[1];
                 valA = beforeInt[regA];
                 regB = op[2];
                 valB = beforeInt[regB];
                 beforeInt[regC] = valA * valB;
                 return beforeInt;
            case MULI:
                regA = op[1];
                valA = beforeInt[regA];
                valB = op[2];
                beforeInt[regC] = valA * valB;
                return beforeInt;
                
            case BANR:
                regA = op[1];
                regB = op[2];
                valA = beforeInt[regA];
                valB = beforeInt[regB];
                beforeInt[regC] = valA & valB;
                return beforeInt;
                
            case BANI:
                regA = op[1];
                valA = beforeInt[regA];
                valB = op[2];
                beforeInt[regC] = valA & valB;
                return beforeInt;
                
            case BORR:
                regA = op[1];
                valA = beforeInt[regA];
                regB = op[2];
                valB = beforeInt[regB];
                beforeInt[regC] = valA | valB;
                return beforeInt;
                
            case BORI:
                regA = op[1];
                valA = beforeInt[regA];
                valB = op[2];
                beforeInt[regC] = valA | valB;
                return beforeInt;
                
            case SETR:
                regA = op[1];
                valA = beforeInt[regA];
                beforeInt[regC] = valA;
                return beforeInt;
                
            case SETI:
                valA = op[1];
                beforeInt[regC] = valA;
                return beforeInt;
                
            case GTRR:
                regA = op[1];
                regB = op[2];
                valA = beforeInt[regA];
                valB = beforeInt[regB];
                beforeInt[regC] = valA>valB ? 1 : 0;
                return beforeInt;
                
            case GTIR:
                valA = op[1];
                regB = op[2];
                valB = beforeInt[regB];
                beforeInt[regC] = valA>valB ? 1 : 0;
                return beforeInt;
                
            case GTRI:
                regA = op[1];
                valB = op[2];
                valA = beforeInt[regA];
                beforeInt[regC] = valA>valB ? 1 : 0;
                return beforeInt;
                
            case EQRR:
                regA = op[1];
                regB = op[2];
                valA = beforeInt[regA];
                valB = beforeInt[regB];
                beforeInt[regC] = valA == valB ? 1 : 0;
                return beforeInt;
            
            case EQIR:
                valA = op[1];
                regB = op[2];
                valB = beforeInt[regB];
                beforeInt[regC] = valA == valB ? 1 : 0;
                return beforeInt;
            
            case EQRI:
                regA = op[1];
                valA = beforeInt[regA];
                valB = op[2];
                beforeInt[regC] = valA == valB ? 1 : 0;
                return beforeInt;
            
        }
        return null;

    }
}
