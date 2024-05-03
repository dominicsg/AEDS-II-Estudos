import java.util.Scanner;

class I_Heapsort {
    static void heapsort(int[] array) {
        // int comp = 0, mov = 0;

        // Alterar o vetor ignorando a posicao zero
        int[] tmp = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            tmp[i + 1] = array[i];
        }
        array = tmp;

        // Constroi Heap
        for (int tamHeap = 2; tamHeap <= array.length; tamHeap++) {
            construir(tamHeap, array);
        }

        // Ordenando
        int tamHeap = array.length;
        while (tamHeap > 1) {
            swap(1, tamHeap--, array);
            reconstruir(tamHeap, array);
        }

        // Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = tmp[i + 1];
        }

        // System.out.println("Foram feitas " + comp + " comparacoes e " + mov + "
        // movimentacoes.");
    }

    static void construir(int tamHeap, int[] array) {
        for (int i = tamHeap; i > 1 && array[i] > array[i / 2]; i /= 2) {
            swap(i, i / 2, array);
        }
    }

    static void reconstruir(int tamHeap, int[] array) {
        int i = 1;
        // while (hasFilho(i, tamHeap) == true) {
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(i, tamHeap, array);
            if (array[i] < array[filho]) {
                swap(i, filho, array);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    static int getMaiorFilho(int i, int tamHeap, int[] array) {
        int filho;
        if (2 * i == tamHeap || array[2 * i] > array[2 * i + 1]) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    static boolean hasFilho(int i, int tamHeap, int[] array) {
        return (i <= (tamHeap / 2));
    }

    static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        int[] array = new int[50];
        ArrayIO.preencheAleatoriamenteParcial(array, 42);
        ArrayIO.imprimeArray(array);

        long startTime = System.currentTimeMillis();
        heapsort(array);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execucao: " + executionTime + " ms");

        ArrayIO.checkOrdenado(array);
        ArrayIO.imprimeArray(array);

        Sc.close();
    }
}