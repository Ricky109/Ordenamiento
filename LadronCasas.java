package ordenamiento;

public class LadronCasas {
	public static int robarMax(int[] vals, int capacidad) {
        // Verificar si no hay casas o la capacidad de la bolsa es 0
        if (vals == null || vals.length == 0 || capacidad == 0) {
            return 0;
        }

        int numCas = vals.length;

        // Crear una tabla para almacenar los valores máximos posibles
        int[][] opc = new int[numCas + 1][capacidad + 1];

        // Llenar la tabla utilizando programación dinámica
        for (int i = 1; i <= numCas; i++) {
            for (int j = 1; j <= capacidad; j++) {
                // Si la casa i es robable con la capacidad actual de la bolsa
                if (vals[i - 1] <= j) {
                    // Considerar la opción de robar la casa i y la mejor opción anterior
                	opc[i][j] = Math.max(opc[i - 1][j], opc[i - 1][j - vals[i - 1]] + vals[i - 1]);
                } else {
                    // Si la casa i no es robable, copiar la mejor opción anterior
                	opc[i][j] = opc[i - 1][j];
                }
            }
        }

        // El valor máximo que se puede robar se encuentra en la última celda de la tabla
        return opc[numCas][capacidad];
    }

    
    
    public static int ladronPeso(int capacidad, int[] peso, int[] valor, int n) {
        // Crear una tabla para almacenar los valores máximos posibles
        int[][] opc = new int[n + 1][capacidad + 1];

        // Llenar la tabla utilizando programación dinámica
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacidad; w++) {
                // Si el peso del objeto es menor o igual a la capacidad actual de la mochila
                if (peso[i - 1] <= w) {
                    // Considerar la opción de robar el objeto i y la mejor opción anterior
                	opc[i][w] = Math.max(valor[i - 1] + opc[i - 1][w - peso[i - 1]], opc[i - 1][w]);
                } else {
                    // Si el peso del objeto es mayor que la capacidad actual de la mochila, copiar la mejor opción anterior
                	opc[i][w] = opc[i - 1][w];
                }
            }
        }

        // El valor máximo que se puede obtener se encuentra en la última celda de la tabla
        return opc[n][capacidad];
    }
    
 // Método de prueba
    public static void main(String[] args) {
        int capacidad1 = 8;
        int[] values = {2, 5, 7, 2, 11, 4};
        int[] weights = {3, 1, 4, 2, 6, 5};
        int n = values.length;
        System.out.println("Valor máximo que se puede obtener: " + LadronCasas.ladronPeso(capacidad1, weights, values, n));
        
        int[] nums = {1, 2, 3, 4, 5};
        int capacidad2 = 8;
        System.out.println("Cantidad máxima que se puede robar: " + LadronCasas.robarMax(nums, capacidad2));
    }

}
