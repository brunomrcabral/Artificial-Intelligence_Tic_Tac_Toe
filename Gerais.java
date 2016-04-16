
public class Gerais {
    static int getScore(Matrix m, int jogador) {  // Adicionei o unsafe , para o ciclo caso uma posiçao crucial esteja ocupada
        int score = 0;
        int plays = 0;

        // Horizontal
        for (int c=0; c<3; c++) {
            plays = 0;
            boolean unsafe = false;
            for (int d=0; d<3; d++) {
                if ((m.matriz[c][d] != jogador) && (m.matriz[c][d] != 0)) {
                    unsafe = true;
                    break;
                } else if (m.matriz[c][d] == jogador) plays++;
            }
            if (!unsafe) {
                score++;
                if (plays == 3) return 1000;
            }
        }

        // Vertical
        for (int c=0; c<3; c++) {
            plays = 0;
            boolean unsafe = false;
            for (int d=0; d<3; d++) {
                if ((m.matriz[d][c] != jogador) && (m.matriz[d][c] != 0)) {
                    unsafe = true;
                    break;
                } else if (m.matriz[d][c] == jogador) plays++;
            }
            if (!unsafe) {
                score++;
                if (plays == 3) return 1000;
            }
        }

        // Diagonal Normal
        plays = 0;
        for (int c=0; c<3; c++) {
            boolean unsafe = false;
            if ((m.matriz[c][c] != jogador) && (m.matriz[c][c] != 0)) {
                unsafe = true;
                break;
            } else if (m.matriz[c][c] == jogador) plays++;
            if (!unsafe) {
                score++;
                if (plays == 3) return 1000;
            }
        }
        // Diagonal oposta
        plays = 0;
        for (int c=0; c<3; c++) {
            boolean unsafe = false;
            if ((m.matriz[c][2-c] != jogador) && (m.matriz[c][2-c] != 0)) {
                unsafe = true;
                break;
            } else if (m.matriz[c][2-c] == jogador) plays++;
            if (!unsafe) {
                score++;
                if (plays == 3) return 1000;
            }
        }
        return score;
    }
    //---------------------------------------------------------------------------------------------------
    static void sucesso(int jogador,int nos_gerados) {
        if (jogador > 0) {
            System.out.println("Jogador "+(jogador==1?'x':'o')+" Ganhou!");
        } else {
            System.out.println("Empate!");

        }
        System.out.println("Nós processados: "+ nos_gerados);
        System.exit(0);
    }
    //---------------------------------------------------------------------
    static int getMinValue(int[] num) {
        if (num.length == 0)
            return -1;
        int minimo = num[0];
        for (int i=1; i<num.length; i++){
            if (num[i] < minimo)
                minimo = num[i];
        }
        return minimo;
    }
    //---------------------------------------------------------------------------------------------------
    static int getMaxIndex(int[] num) {
        if (num.length == 0)
            return -1;
        int maximoIndex = 0;
        int maximo = num[0];
        for (int i=1; i<num.length; i++){
            if (num[i] > maximo) {
                maximo = num[i];
                maximoIndex = i;

            }
        }
        return maximoIndex;
    }

}




