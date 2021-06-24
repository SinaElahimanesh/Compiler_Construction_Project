import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
//        String str = "(A + B).c = d";
//        String output2 = parser(str);
//        System.out.println(output2);
        try {
            String inputFileName = null;
            String outputFileName = null;
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-i")) {
                        inputFileName = args[i + 1];
                    }
                    if (args[i].equals("-o")) {
                        outputFileName = args[i + 1];
                    }
                }
            }
            Reader reader = null;
            Writer writer = null;
            if (inputFileName != null)
                reader = new FileReader("tests/" + inputFileName);
            if (outputFileName != null)
                writer = new FileWriter("out/" + outputFileName);
            // Read with reader and write the output with writer.

            int data = reader.read();
            StringBuilder input = new StringBuilder();
            while (data != -1) {
                input.append((char) data);
                data = reader.read();
            }
            reader.close();
            String output = parser(input.toString());
            System.out.println(output);
            writer.write(output);
            writer.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String parser(String input) {
        CompilerScanner scanner = new CompilerScanner(input);
        CodeGenerator cg = new CodeGenerator() {
            int call_number = 0;
            int current_call = 0;
            @Override
            public void doSemantic(String sem, Action action) {
                System.out.println(sem);
                if (action != Action.SHIFT)
                    return;
                current_call ++;
                if (sem.equals("is_lvalue_friendly"))
                    call_number = current_call;
                else if (sem.equals("assignment_operator")) {
                    if (current_call - call_number > 2) {
                        System.out.println("##########################");
                        System.out.println(current_call);
                        System.out.println(call_number);
                        throw new RuntimeException("wrong assignment");
                    } else {
                        System.out.println("##########################");
                        System.out.println(current_call);
                        System.out.println(call_number);
                    }
                }
            }
        };
        Parser parser = new Parser(scanner, cg, "src/table.npt", true);
        try {
            parser.parse();
            return "OK";
        } catch (RuntimeException ignored) {
            return "Syntax Error";
        }
    }

    public static String scanner(String input) {
        CompilerScanner compilerScanner = new CompilerScanner("");
        String output = compilerScanner.startScanning(input);
        System.out.println(output);
        return output;
    }


//    public static void main(String[] args) {
//
//        String input1 = "14.2a;sldjf”okok”false f noooo 0x1234fe ";
//        String input2 = "class Program { void main () {} }";
//        String input3 = "{−123−a35 , id3a ,+∗;}[||===!=()&&]<><=>== a[24]=”7”; n!=if;\n" +
//                "false,−if;true32; forpar";
//
////        Scanner scanner = new java.util.Scanner(System.in);
////        StringBuilder input = new StringBuilder("");
////        while (scanner.hasNext()) {
////            String token = scanner.nextLine();
////            token += " \n";
////            input.append(token);
////        }
////        scanner.close();
//
//        String test1 = "abcdefg\n" +
//                "Stephen\n" +
//                "stephen_hawking\n" +
//                "six_dot_035\n" +
//                "foo_\n" +
//                "covid_19\n";
//
//        String test2 = "void\n" +
//                "int\n" +
//                "double\n" +
//                "bool\n" +
//                "string\n" +
//                "class\n" +
//                "interface\n" +
//                "null\n" +
//                "this\n" +
//                "extends\n" +
//                "implements\n" +
//                "for\n" +
//                "while\n" +
//                "if\n" +
//                "else\n" +
//                "return\n" +
//                "break\n" +
//                "new\n" +
//                "NewArray\n" +
//                "Print\n" +
//                "ReadInteger\n" +
//                "ReadLine\n" +
//                "getArrVal\n";
//
//        String test3 = "foo bar\n" +
//                "baz\tquux\n" +
//                "meep  \t\t\t\t  peem\n" +
//                "whaah\fboom\n" +
//                "doom\n" +
//                "\n" +
//                "\n" +
//                "\f\t\t\f\n" +
//                "gloom    loom\tweave\n";
//
//        String test4 = "1\n" +
//                "01\n" +
//                "10\n" +
//                "65536\n" +
//                "2147483647\n";
//
//        String test5 = "0x0\n" +
//                "0x1\n" +
//                "0xe43620\n" +
//                "0x11\n" +
//                "0xbeef\n" +
//                "0xF\n" +
//                "0xF00\n" +
//                "0xB1ad\n";
//
//        String test6 = "7.7\n" +
//                "0.123456\n" +
//                "2020.\n" +
//                "0.0\n" +
//                "1.99999999\n" +
//                "0.99999999\n";
//
//        String test7 = "+ - * < <= != && += -= *= /=\n";
//
//        String test8 = "//this is a single line comment\n" +
//                "this is not a comment\n";
//
//        String test9 = "10\n" +
//                "9.6\n" +
//                "\"Hello\"\n" +
//                "true\n" +
//                "false\n";
//
//        String test10 = "// this is a single line comment\n" +
//                "this is not a comment\n";
//
//        String test11 = "{-123-a35,id3a,+*;}[||===!=()&&]<><=>==\n" +
//                "    a[24]=\"7\"; n!=if;\n" +
//                "false,-if;true32;\n" +
//                "forpar\n";
//
//        String test12 = "class Program { void main () {} }";
//
//        String test13 = "/* akdf;asdf;ljas;lf\n" +
//                "adfadfgg\n" +
//                "*/ askdfahskdf";
//
//        CompilerScanner compilerScanner = new CompilerScanner();
//        System.out.println(compilerScanner.startScanning(test12));
//    }
}
