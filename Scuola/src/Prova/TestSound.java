import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

public class TestSound {

    private static int testN;
    private static int passed;
    private static String code;

    public static <T> boolean testDouble(String code, T arg, ToDoubleFunction<T> f, double expected) {
        try {
            System.out.println(code);
            testN++;
            if (arg != null) {
                double result = f.applyAsDouble(arg);
                if (result == expected) {
                    passed++;
                    reportOk();
                    return true;
                } else {
                    reportFail("Errore: atteso="+expected+" ottenuto="+passed);
                }
            }
        } catch (Exception e) {
            reportFail("Sollevata eccezione: "+e.getClass().getName());
        }
        return false;
    }

    public static <T> boolean testInt(String code, T arg, ToIntFunction<T> f, int expected) {
        try {
            System.out.println(code);
            testN++;
            if (arg != null) {
                int result = f.applyAsInt(arg);
                if (result == expected) {
                    passed++;
                    reportOk();
                    return true;
                } else {
                    reportFail("Errore: atteso="+expected+" ottenuto="+result);
                }
            }
        } catch (Exception e) {
            reportFail("Sollevata eccezione: "+e.getClass().getName());
        }
        return false;
    }

    public static <T> boolean testBoolean(String code, T arg, Predicate<T> f, boolean expected) {
        try {
            System.out.println(code);
            testN++;
            if (arg != null) {
                boolean result = f.test(arg);
                if (result == expected) {
                    passed++;
                    reportOk();
                    return true;
                } else {
                    reportFail("Errore: atteso="+expected+" ottenuto="+result);
                }
            }
        } catch (Exception e) {
            reportFail("Sollevata eccezione: "+e.getClass().getName());
        }
        return false;
    }


    public static <T> boolean testException(String code, Runnable f, Class<?> expected) {
        testN++;
        System.out.println(code);
        try {
            f.run();
        } catch (Exception e) {
            if (e.getClass().equals(expected)) {
                reportOk();
                passed++;
                return true;
            }  else {
                reportFail("Attesa eccezione "+expected.getName()+" sollevata "+e.getClass().getName());
            }
        }
        return false;
    }

    private static void reportOk() {
        System.out.println("["+testN+"]: OK!\n");
    }

    private static void reportFail(String message) {
        System.out.println("["+testN+"]: KO! "+message+"\n");
    }

    public static void main(String[] argv) {
        testException("Sound snd = new Sound(-1,100)", () -> new Sound(-1, 100), IllegalArgumentException.class );
        testException("Sound snd = new Sound(2,-10)", () -> new Sound(2, -10), IllegalArgumentException.class );
        testException("Sound snd = new Sound(-5,-20)", () -> new Sound(100, 10), IllegalArgumentException.class );

        runTest1();
        runTest2();
        runTest3();
        runTest4();
        runTest5();
        runTest6();
        runTest7();

        System.out.println("Passati "+passed+" test di "+testN);
    }

    private static void runTest1() {
        try {
            System.out.println("Sound snd = new Sound(1000,10);");
            Sound snd = new Sound(1000, 10);
            testInt("snd.elements()", snd, Sound::elements, 0);
            testInt("snd.size()",snd, Sound::size,1000);
            for(int i=0; i<20; i++) {
                double value = (i%10)*Math.pow(-1,i);
                testBoolean("snd.put("+value+");",snd,arg -> arg.put(value),true);
                testInt("snd.elements()",snd,Sound::elements,i+1);
            }
            testInt("snd.size()",snd, Sound::size,1000);
            for(int i=0; i<20; i++) {
                double value = (i%10)*Math.pow(-1,i);
                int idx = i;
                testDouble("snd.get("+i+");",snd,arg -> arg.get(idx),value);
            }
        } catch (Exception e) {
            reportFail("Sollevata eccezione inattesa "+e.getClass().getName());
            e.printStackTrace();
        }
    }

    private static void runTest2() {
        try {
            System.out.println("Sound snd1 = new Sound(1000,10);");
            Sound snd = new Sound(1000, 10);
            testInt("snd1.elements()", snd, Sound::elements, 0);
            for(int i=0; i<20; i++) {
                double value = 2*(i%10)*Math.pow(-1,i);
                testBoolean("snd1.get("+value+");",snd,arg -> arg.put(value),Math.abs(value)<10);
            }
        } catch (Exception e) {
            reportFail("Sollevata eccezione inattesa "+e.getClass().getName());
            e.printStackTrace();
        }
    }

    private static void runTest3() {
        try {
            System.out.println("Sound snd = new Sound(1000,10);");
            Sound snd = new Sound(1000, 10);
//            testInt("snd.inversion()",snd, arg -> arg.isAPointOfInversion(0),false);
            for(int i=0; i<20; i++) {
                double value = (i%10)*Math.pow(-1,i);
                testBoolean("snd.put("+value+");",snd,arg -> arg.put(value),true);
            }
//            testInt("snd.inversion()",snd, Sound::inversion,16);
        } catch (Exception e) {
            reportFail("Sollevata eccezione inattesa "+e.getClass().getName());
            e.printStackTrace();
        }
    }

    private static void runTest4() {
        try {
            System.out.println("Sound snd = new Sound(1000,10);");
            Sound snd = new Sound(1000, 10);
            testInt("snd.elements()", snd, Sound::elements, 0);
            snd.isAPointOfInversion(0);
            testBoolean("snd.isAPointOfInversion(0)",snd, arg -> arg.isAPointOfInversion(0),false);
            testBoolean("snd.put(5);",snd,arg -> arg.put(5),true);
            testBoolean("snd.isAPointOfInversion(0)",snd, arg -> arg.isAPointOfInversion(0),false);
            testBoolean("snd.put(-5);",snd,arg -> arg.put(-5),true);
            testBoolean("snd.isAPointOfInversion(0)",snd, arg -> arg.isAPointOfInversion(0),true);
            testBoolean("snd.isAPointOfInversion(1)",snd, arg -> arg.isAPointOfInversion(1),false);
            testBoolean("snd.put(-4);",snd,arg -> arg.put(-4),true);
            testBoolean("snd.isAPointOfInversion(1)",snd, arg -> arg.isAPointOfInversion(1),false);
        } catch (Exception e) {
            reportFail("Sollevata eccezione inattesa "+e.getClass().getName());
            e.printStackTrace();
        }
    }

    private static void runTest5() {
        try {
            System.out.println("Sound snd = new Sound(1000,10);");
            Sound snd = new Sound(1000, 10);
            for(int i=0; i<20; i++) {
                double value = (i%10)*Math.pow(-1,i);
                testBoolean("snd.put("+value+");",snd,arg -> arg.put(value),true);
                testBoolean("snd.put("+0+");",snd,arg -> arg.put(0),true);
            }
//            testInt("snd.inversion()",snd, Sound::inversion,0);
        } catch (Exception e) {
            reportFail("Sollevata eccezione inattesa "+e.getClass().getName());
            e.printStackTrace();
        }
    }

    private static void runTest6() {
        try {
            System.out.println("Sound snd = new Sound(1000,10);");
            Sound snd = new Sound(1000, 10);
            for(int i=0; i<6; i++) {
                double value = (i%10)*Math.pow(-1,i);
                System.out.println("snd.put("+value+");");
                snd.put(value);
            }
            for(int i=0;i<6;i++) {
                int idx = i;
                testBoolean("snd.isAPointOfInversion("+idx+")",snd, arg -> arg.isAPointOfInversion(idx), (idx!=0)&&(idx!=5));
            }
        } catch (Exception e) {
            reportFail("Sollevata eccezione inattesa "+e.getClass().getName());
            e.printStackTrace();
        }
    }

    private static void runTest7() {
        try {
            System.out.println("Sound snd = new Sound(1000,10);");
            Sound snd = new Sound(1000, 10);
            for(int i=0; i<10; i++) {
                double value = 10*Math.sin(2*Math.PI*i/10);
                System.out.println("snd.put("+value+");");
                snd.put(value);
            }
            int[] res1 = new int[] {0, 1, 2, 3, 4, 5};
            int[] res2 = new int[] {0, 6, 7, 8, 9};
            int[] res3 = new int[] {0, 5};
            int[] res4 = new int[] {0};
            int[] res5 = new int[0];

            System.out.println("snd.between(0,10);");
            checkArray(res1, snd.between(0,10));
            System.out.println("snd.between(-10,0);");
            checkArray(res2, snd.between(-10,0));
            System.out.println("snd.between(-5,5);");
            checkArray(res3, snd.between(-5,5));
            System.out.println("snd.between(0,0);");
            checkArray(res4, snd.between(0,0));
            System.out.println("snd.between(11,20);");
            checkArray(res5, snd.between(11,20));

        } catch (Exception e) {
            reportFail("Sollevata eccezione inattesa "+e.getClass().getName());
            e.printStackTrace();
        }
    }

    private static void checkArray(int[] expected, int[] actual) {
        testN++;
        if (expected.length == actual.length) {
            passed++;
            reportOk();
        } else {
            reportFail("Expected an array of lenght "+expected.length+" is "+actual.length);
        }
        testN++;
        if (Arrays.equals(expected, actual))  {
            passed++;
            reportOk();
        } else {
            reportFail("Expected "+Arrays.toString(expected)+" is "+Arrays.toString(actual));
        }

    }

}
