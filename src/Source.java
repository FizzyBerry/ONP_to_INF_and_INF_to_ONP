//Maria Widera - gr. 4
import java.util.Scanner;


public class Source {
    static String Wyjscie="";

    static char[] tab;   //stos
    static int top;        //wierzcholek stosu

    public static void StackS(int MaxSize){        //inicjalizacja stosu i wierzcholka
        tab = new char[MaxSize];
        top = 0;
    }

    static void Push(char item) throws ArrayIndexOutOfBoundsException{  //dodaje na stos element
        if (top < tab.length)
        {
            tab[top] = item;
            top++;
        } else
            throw new ArrayIndexOutOfBoundsException(0);
    }

    static char Pop(){        //zdejmuje ze stosu element
            top--;
        return tab[top];
    }

    static void MultiPop(int p1) {      //zdejmuje więcej elemntow na raz o priorytecie wiekszym badz rownym p
        while(top!=0)
        {
            char op = Pop();
            if( op== '(' )
            {
                Push(op);
                return;
            }
            else
            {
                int p2;

                p2=Priority(op);

                if(p2 < p1)
                {
                    Push(op);
                    return;
                }
                else
                    Wyjscie = Wyjscie + op;
            }
        }
    }

    static int Priority(char op) {          //Priorytety operatorów
        if(op=='=')
            return 0;
        if(op=='<'||op=='>')
            return 1;
        if(op=='+'||op=='-')
            return 2;
        if(op=='*'||op=='/'||op=='%')
            return 3;
        if(op=='^')
            return 4;
        if(op=='~')
            return 5;
        if(op=='('||op==')')
            return 6;
        if(op=='a'||op=='b'||op=='c'||op=='d'||op=='e'||op=='f'||op=='g'||op=='h'||op=='i'||op=='j'||op=='k'||op=='l'||op=='m'||op=='n'||op=='o'||op=='p'||op=='r'||op=='s'||op=='t'||op=='u'||op=='w'||op=='x'||op=='y'||op=='z')
            return 7;
        return -1;
    }

    static void DoNawiasu(){     //zdejmuje operatory do "("
        while(top!=0)
        {
            char op = Pop();

            if( op == '(' )
                return;
            else
                Wyjscie = Wyjscie + op;
        }
    }

    static void Clear(){     //zeruje stos

        for (int i = 0; i < top; i++)
            tab[i] = ' ';

        top = 0;
    }

    /************KLASY DO ONP -> INF**************/

    static class stack {
        private int maxSize;
        private String[] Elem;
        private int top;

        public stack(int size) {
            maxSize = size;

            Elem = new String[maxSize];
            top = maxSize;
        }

        public void push(String x) {
            if(!isFull())
                Elem[--top] = x;
        }

        public String pop() {
            if(isEmpty())
                return "";
            else
                return Elem[top++];
        }

        public String top() {
            if ( isEmpty() )
                return "";
            else
                return Elem[top];
        }

        public boolean isEmpty() {
            return (top == maxSize);
        }

        public boolean isFull() {
            return (top == 0);
        }
    }

    static class stackInt {
        private int maxSize;
        private int[] Elem;
        private int top;

        public stackInt(int size) {
            maxSize = size;

            Elem = new int[maxSize];
            top = maxSize;
        }

        public void push(int x) {
            if(!isFull())
                Elem[--top] = x;
        }

        public int pop() {
            if(isEmpty())
                return 0;
            else
                return Elem[top++];
        }

        public int top() {
            if ( isEmpty() )
                return 0;
            else
                return Elem[top];
        }

        public boolean isEmpty() {
            return (top == maxSize);
        }

        public boolean isFull() {
            return (top == 0);
        }
    }

    static int PriorityONP(String op) {
        if(op.equals("="))
            return 0;
        if(op.equals("<")||op.equals(">"))
            return 1;
        if(op.equals("+")||op.equals("-"))
            return 2;
        if(op.equals("*")||op.equals("/")||op.equals("%"))
            return 3;
        if(op.equals("^"))
            return 4;
        if(op.equals("~"))
            return 5;
        if(op.charAt(0) >= 'a' && op.charAt(0) <= 'z')
            return 7;

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Ilosc_Zestawow=sc.nextInt();
        sc.nextLine();
        StackS(256);
        while(Ilosc_Zestawow!=0) {
            String tmp = sc.nextLine();
            Clear();

            if(tmp.charAt(0) == 'I') {
                System.out.print("ONP: "+INF(tmp));
            }

            if(tmp.charAt(0) == 'O') {
                String[] Wejscie= new String[256];

                for(int i = 5;i<tmp.length();i++)
                    Wejscie[i] = Character.toString(tmp.charAt(i));

                int n = 0;

                for(int j = 5;j<tab.length;j++)
                    if(Wejscie[j] != null)
                        n++;

                String[] tmpArray = new String[n]; // tablica musi byc dokladnie takiej samej długości jak wprowadzony string
                for(int i = 0; i < n; i++)
                    tmpArray[i] = Wejscie[i+5];

                ONP(tmpArray);
            }

            Ilosc_Zestawow--;
            if(Ilosc_Zestawow !=0)
                System.out.println();
        }
    }

    public static String INF(String Wejscie) {

        for(int i=0;i<Wejscie.length();i++){         //usuwanie niewlasciwych znakow
            if(!((Wejscie.charAt (i) >= 'a' && Wejscie.charAt (i) <= 'z')||Wejscie.charAt(i)=='+'||Wejscie.charAt(i)=='-'||Wejscie.charAt(i)=='~'||Wejscie.charAt(i)=='>'||Wejscie.charAt(i)=='<'||Wejscie.charAt(i)=='/'||Wejscie.charAt(i)=='='||Wejscie.charAt(i)=='('||Wejscie.charAt(i)==')'||Wejscie.charAt(i)=='*'||Wejscie.charAt(i)=='^'||Wejscie.charAt(i)=='%')) {
                Wejscie=Wejscie.replace(Wejscie.charAt(i),' ');
            }

        }

        boolean q0=true,q1=false,q2=false,ZgodnoscNawiasow=true;
        int nawias_otwierajacy=0,nawias_zamykajacy=0;

        for(int i=0;i<Wejscie.length();i++){             //automat sprawdzajacy poprawnosc wyrazenia w INF
            if(Wejscie.charAt(i)!=' ') {
                if(q0){              //stan q0

                    if(Wejscie.charAt(i)=='(') {
                            nawias_otwierajacy++;
                    }
                    else {
                        if(Wejscie.charAt(i)=='~') {
                            q2=true;
                            q0=false;
                        }
                        else {
                            if(Wejscie.charAt (i) >= 'a' && Wejscie.charAt (i) <= 'z') {
                                q1=true;
                                q0=false;
                            }
                            else {
                                return "error";
                            }
                        }
                    }
                }
                else {
                    if(q1){              //stan q1
                        if(Wejscie.charAt(i)==')') {
                                nawias_zamykajacy++;
                                if (nawias_otwierajacy < nawias_zamykajacy) {
                                    ZgodnoscNawiasow = false;
                                }
                        }
                        else {
                            if(Wejscie.charAt(i)=='+'||Wejscie.charAt(i)=='-'||Wejscie.charAt(i)=='>'||Wejscie.charAt(i)=='<'||Wejscie.charAt(i)=='/'||Wejscie.charAt(i)=='='||Wejscie.charAt(i)=='*'||Wejscie.charAt(i)=='^'||Wejscie.charAt(i)=='%') {
                                q0=true;
                                q1=false;
                            }
                            else {
                                return "error";
                            }
                        }
                    }
                    else{                //stan q2

                        if(Wejscie.charAt(i)=='(') {
                            nawias_otwierajacy++;
                            q2=false;
                            q0=true;
                        }
                        else {
                            if(Wejscie.charAt (i) >= 'a' && Wejscie.charAt (i) <= 'z') {
                                q1=true;
                                q2=false;
                            }
                            else{

                                if(Wejscie.charAt(i)!='~') {
                                    return "error";
                                }
                            }
                        }
                    }
                }
            }
        }
        if(!(nawias_otwierajacy==nawias_zamykajacy))
            return "error";

        if(!q1)
            return "error";

        if(!ZgodnoscNawiasow){
            return "error";
        }

        /**************           KONWERSJA NA ONP             **************/
        Wyjscie="";

        for(int i=0;i<Wejscie.length();i++) {
            if(Wejscie.charAt(i)!=' ') {
                switch(Wejscie.charAt(i)) {
                    case '=':
                        MultiPop(1);
                        Push(Wejscie.charAt(i));
                        break;

                    case '>':
                    case '<':
                        MultiPop(1);
                        Push(Wejscie.charAt(i));
                        break;

                    case '+':
                    case '-':
                        MultiPop(2);
                        Push(Wejscie.charAt(i));
                        break;

                    case '*':
                    case '/':
                    case '%':
                        MultiPop(3);
                        Push(Wejscie.charAt(i));
                        break;

                    case '^':
                        MultiPop(5);
                        Push(Wejscie.charAt(i));
                        break;

                    case '~':
                        Push(Wejscie.charAt(i));
                        break;

                    case '(':
                        Push(Wejscie.charAt(i));
                        break;

                    case ')':
                        DoNawiasu();
                        break;

                    default:
                        Wyjscie+=Wejscie.charAt(i);
                        break;
                }
            }
        }

        while(top!=0){               //zdjecie reszty znakow ze stosu
            Wyjscie += Pop();
        }



        return Wyjscie;
    }

    /**************           KONWERSJA NA INF             **************/

     static void ONP(String[] wejscie)
    {
        for(int i=0;i<wejscie.length;i++){         //usuwanie niewlasciwych znakow
            if(!((wejscie[i].charAt(0) >= 'a' && wejscie[i].charAt(0) <= 'z')||wejscie[i].charAt(0)=='+'||wejscie[i].charAt(0)=='-'||wejscie[i].charAt(0)=='~'||wejscie[i].charAt(0)=='>'||wejscie[i].charAt(0)=='<'||wejscie[i].charAt(0)=='/'||wejscie[i].charAt(0)=='='||wejscie[i].charAt(0)=='*'||wejscie[i].charAt(0)=='^'||wejscie[i].charAt(0)=='%')) {
                wejscie[i]=wejscie[i].replace(wejscie[i].charAt(0),' ');
            }

        }
        stack Stack = new stack(wejscie.length);
        stackInt Priority = new stackInt(wejscie.length);

        String output ;
        int liczbaOperandow = 0, liczbaOperatorow = 0;

        for(int i = 0;i<wejscie.length;i++) {
            if(!wejscie[i].equals("(") && !wejscie[i].equals(")")&& !wejscie[i].equals(" "))
                if(wejscie[i].charAt(0) >= 'a' && wejscie[i].charAt(0) <= 'z'){          //element jest operandem

                    Stack.push(wejscie[i]);
                    Priority.push(PriorityONP(wejscie[i]));
                    liczbaOperandow++;
                }
                else{
                    if(!(wejscie[i].equals("~")||wejscie[i].equals("=")||wejscie[i].equals(">")||wejscie[i].equals("<")||wejscie[i].equals("^"))){         //operator inny niż "~" lub "=" lub "<" lub ">" lub "^"
                        liczbaOperatorow++;

                        if(Priority.top() <= PriorityONP(wejscie[i]))
                            output = "(" + Stack.pop() + ")";
                        else
                            output = Stack.pop();

                        Priority.pop();

                        if(Priority.top() < PriorityONP(wejscie[i]))
                            output = "(" + Stack.pop() + ")" + wejscie[i] + output;
                        else
                            output = Stack.pop() + wejscie[i] + output;

                        Priority.pop();
                    }
                    else {       //operator "~" lub "=" lub "<" lub ">" lub "^"
                        if(wejscie[i].equals("~")) {
                            if (Priority.top() < PriorityONP(wejscie[i]))
                                output = wejscie[i] + "(" + Stack.pop() + ")";
                            else
                                output = wejscie[i] + Stack.pop();

                            Priority.pop();
                        }
                        else{
                            liczbaOperatorow++;

                            if(Priority.top() < PriorityONP(wejscie[i]))
                                output = "(" + Stack.pop() + ")";
                            else
                                output = Stack.pop();

                            Priority.pop();

                            if(Priority.top() < PriorityONP(wejscie[i]))
                                output = "(" + Stack.pop() + ")" + wejscie[i] + output;
                            else
                                output = Stack.pop() + wejscie[i] + output;

                            Priority.pop();
                        }
                    }

                    Stack.push(output);
                    Priority.push(PriorityONP(wejscie[i]));
                }
        }
        if(liczbaOperandow-1  == liczbaOperatorow ) {
            String tmp = Stack.pop();
            if(Stack.isEmpty())
                System.out.print("INF: " + tmp);
            else
                System.out.print("INF: error");
        }
        else
            System.out.print("INF: error");
    }

    }


/*
INPUT:
10
ONP: xabc**=
ONP: ab+a~a-+
INF: a+b+(~a-a)
INF: x=~~a+b*c
INF: t=~a<x<~b
INF: ( a,+ b)/..[c3
ONP: ( a,b,.).c;-,*
ONP: abc++def++g+++
INF: x=a=b=c
ONP: xabc===

OUTPUT:
INF: x=a*(b*c)
INF: a+b+(~a-a)
ONP: ab+a~a-+
ONP: xa~~bc*+=
ONP: ta~x<b~<=
ONP: ab+c/
INF: a*(b-c)
INF: error
ONP: xabc===
INF: x=a=b=c
 */