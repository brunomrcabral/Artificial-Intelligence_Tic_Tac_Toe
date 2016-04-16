import java.util.*;


class Jogo_Do_Galo {
    public static void main(String args[]) {

        int metodo, primeiro;
        int matriz[][] = new int[3][3];
        for (int c=0; c<3; c++) { //Inicializaçao
            for (int d=0; d<3; d++) {
                matriz[c][d] = 0;
            }
        }

        Scanner ler = new Scanner(System.in);
        System.out.println("Algoritmo desejado?");
        System.out.println("1 - Min e Max");
        System.out.println("2 - Alfa e Beta");
        metodo=ler.nextInt();
        Matrix galomatriz = new Matrix(matriz);
        System.out.println("Ser o primeiro a jogar?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        primeiro = ler.nextInt();
        if (metodo==1){
            Minimax.min(primeiro,galomatriz,0);
        }
        else {
            Alphabeta.alfa(primeiro,galomatriz,0);
        }
    }
}

class Matrix{
    int matriz[][];
    int ocupados;
    LinkedList<Matrix> lista_nos = new LinkedList<Matrix>();

    Matrix(int ma[][]) {
        matriz  = new int [3][3];
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                matriz[i][j] = ma[i][j];
                if (ma[i][j] != 0){
                    ocupados++;
                }
            }
        }

    }
    // Transforma a cordenada da matriz em posicao matriz
    boolean play(int jogador, int posicao) {
        int[] cd = new int[2];
        switch (posicao) {
            case 1: cd[0] = 0; cd[1] = 0; break;
            case 2: cd[0] = 0; cd[1] = 1; break;
            case 3: cd[0] = 0; cd[1] = 2; break;
            case 4: cd[0] = 1; cd[1] = 0; break;
            case 5: cd[0] = 1; cd[1] = 1; break;
            case 6: cd[0] = 1; cd[1] = 2; break;
            case 7: cd[0] = 2; cd[1] = 0; break;
            case 8: cd[0] = 2; cd[1] = 1; break;
            case 9: cd[0] = 2; cd[1] = 2; break;
            default: return false;
        }

        if (isEmpty(cd)) {
            matriz[cd[0]][cd[1]] = jogador;
            ocupados++;
            return true;
        }
        return false;
    }

    boolean isEmpty(int[] posicao) {
        return (matriz[posicao[0]][posicao[1]] == 0);
    }

    void printMatriz() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (j>0) {
                    System.out.print("|");
                }
                System.out.print((matriz[i][j]==1?"x":(matriz[i][j]==2?"o":" ")));
            }
            System.out.println();
        }
        System.out.println();
    }

}
