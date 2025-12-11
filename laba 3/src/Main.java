import functions.*;
public class Main {
    public static void main(String[] args) {
        try {
            // Тестирование ArrayTabulatedFunction
            System.out.println("=== Тестирование ArrayTabulatedFunction ===");
            testTabulatedFunction(new ArrayTabulatedFunction(1, 7, 7));

            // Тестирование LinkedListTabulatedFunction
            System.out.println("\n=== Тестирование LinkedListTabulatedFunction ===");
            testTabulatedFunction(new LinkedListTabulatedFunction(1, 6, 6));
            double[] values = new double[10];
            for(int i = 0; i<10;i++){
                values[i] = Math.random()*(100.0-1.0)+1.0;
            }
            System.out.println("\n=== Тестирование объекта TabulatedFunction с различными значениями y ===");
            testTabulatedFunction(new LinkedListTabulatedFunction(1,10,values));

            // Тестирование исключений
            System.out.println("\n=== Тестирование исключений ===");
            testExceptions();

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testTabulatedFunction(TabulatedFunction function) {
        System.out.println("Функция: ");
        function.showPoints();
        System.out.println("Левая граница: " + function.getLeftDomainBorder());
        System.out.println("Правая граница: " + function.getRightDomainBorder());
        System.out.println("Количество точек: " + function.getPointsCount());
        System.out.println("f(2.5) = " + function.getFunctionValue(2.5));

        try {
            // Добавление точки
            function.addPoint(new FunctionPoint(2.5, 6.25));
            System.out.println("После добавления точки (2.5; 6.25): ");
            function.showPoints();
            // Изменение точки
            function.setPointY(2, 10.0);
            System.out.println("После изменения Y точки 2: ");
            function.showPoints();

            function.deletePoint(0);
            System.out.println("После удаления точки 0: ");
            function.showPoints();
            function.setPointX(2, 1.5);
            System.out.println("После изменения X точки 2: ");
            function.showPoints();
            function.addPoint(new FunctionPoint(1,5));
            System.out.println("После добавления точки (1; 5): ");
            function.showPoints();

        } catch (InappropriateFunctionPointException e) {
            System.out.println("Исключение при работе с точкой: " + e.getMessage());
        }
    }

    private static void testExceptions() {
        try {
            // Некорректный конструктор
            TabulatedFunction func1 = new ArrayTabulatedFunction(5, 0, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано IllegalArgumentException: " + e.getMessage());
        }

        try {
            TabulatedFunction func = new ArrayTabulatedFunction(0, 5, 3);
            // Выход за границы
            func.getPoint(10);
        } catch (FunctionPointIndexOutOfBoundsException e) {
            System.out.println("Поймано FunctionPointIndexOutOfBoundsException: " + e.getMessage());
        }

        try {
            TabulatedFunction func = new ArrayTabulatedFunction(0, 5, 3);
            // Нарушение упорядоченности
            func.setPoint(1, new FunctionPoint(0, 0));
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Поймано InappropriateFunctionPointException: " + e.getMessage());
        }

        try {
            TabulatedFunction func = new ArrayTabulatedFunction(0, 5, 3);
            // Удаление при недостаточном количестве точек
            func.deletePoint(0);
            func.deletePoint(0);
        } catch (IllegalStateException e) {
            System.out.println("Поймано IllegalStateException: " + e.getMessage());
        }
    }
}
