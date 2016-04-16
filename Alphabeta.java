import java.util.Scanner;
public class Alphabeta {
    public static void alfa(int primeiro, Matrix galomatriz, int escolha) {
        int k = 1;
        Scanner ler = new Scanner(System.in);
        AlphaBeta game2 = new AlphaBeta();
        if (primeiro == 1) {
            System.out.println("Sua vez de jogar: 1 a 9");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(k + "|");
                    k++;
                }
                System.out.println();

            }
            escolha = ler.nextInt();
            game2.search(galomatriz, 2, escolha);
        } else game2.search(galomatriz, 1, escolha);
    }

    public static class AlphaBeta {
        int nos_gerados = 0;
        Matrix jogador2_nos[] = new Matrix[9];
        int jogador2_minimo[] = new int[700];
        int jogador2_index = 0;
        Scanner ler = new Scanner(System.in);
        int beta = 100;

        AlphaBeta() {
        }

        void search(Matrix m, int jogador, int jog) {
            // Jogar onde é premitido
            if (jog != 0) {
                Matrix newmatrix = new Matrix(m.matriz);
                newmatrix.play(jogador, jog);
                nos_gerados++;
                m.lista_nos.add(newmatrix);
                jog = 0;
                search(newmatrix, 1, jog);
            }

            if (m.ocupados > 0) {
                int posicao = 0;
                for (int c = 0; c < 3; c++) {
                    for (int d = 0; d < 3; d++) {
                        ++posicao;
                        if (m.matriz[c][d] == 0) {
                            Matrix newmatrix = new Matrix(m.matriz);
                            newmatrix.play(jogador, posicao);
                            nos_gerados++;
                            m.lista_nos.add(newmatrix);
                        }
                    }
                }
            } else {
                Matrix newmatrix;
                newmatrix = new Matrix(m.matriz);
                newmatrix.play(jogador, 5);
                nos_gerados++;
                m.lista_nos.add(newmatrix);
            }
            if (m.ocupados == 9) {
                Gerais.sucesso(0,nos_gerados);
                return;
            }

            // Escolher onde jogar
            if (jogador == 1) {
                jogador2_nos = new Matrix[m.lista_nos.size()];
                jogador2_minimo = new int[m.lista_nos.size()];

                // Resultados
                while (!m.lista_nos.isEmpty()) {
                    search(m.lista_nos.poll(), 2, jog);
                }

                // Melhor resultado
                int goodIndex = Gerais.getMaxIndex(jogador2_minimo);
                jogador2_nos[goodIndex].printMatriz();
                if (Gerais.getScore(jogador2_nos[goodIndex], 1) == 1000) {
                    Gerais.sucesso(1, nos_gerados);
                    return;
                } else {
                    if (jogador2_nos[goodIndex].ocupados < 9) {
                        System.out.println("Sua vez de jogar: 1 a 9");
                        resetVars();
                        boolean valid = false;
                        while (!valid) {
                            valid = jogador2_nos[goodIndex].play(2, ler.nextInt());
                            if (!valid) System.out.println("Posição ocupada");
                        }
                        if (Gerais.getScore(jogador2_nos[goodIndex], 2) == 1000) {
                            Gerais.sucesso(2, nos_gerados);
                            return;
                        } else search(jogador2_nos[goodIndex], 1, jog);
                    } else {
                        Gerais.sucesso(0, nos_gerados);
                        return;
                    }
                }
            } else {
                jogador2_nos[jogador2_index] = m;
                // Pior resultado
                int[] res = new int[m.lista_nos.size()];
                int c = 0;
                while (!m.lista_nos.isEmpty()) {
                    Matrix mpoll = m.lista_nos.poll();
                    res[c] = Gerais.getScore(mpoll, 1) - Gerais.getScore(mpoll, 2);
                    if (res[c] <= beta) {
                        nos_gerados -= (res.length - c);
                        break;
                    }
                    ++c;
                }
                jogador2_minimo[jogador2_index] = Gerais.getMinValue(res);
                beta = jogador2_minimo[jogador2_index];
                ++jogador2_index;
            }
        }


        //--------------------------------------------------------------------
        void resetVars() {
            jogador2_index = 0;
        }
    }
}

