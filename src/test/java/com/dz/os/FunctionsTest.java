package com.dz.os;

import com.dz.os.exceptions.OSException;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionsTest {

    //    before: [1, 1, 0, 0, 1]
    //    after: [1, 0, 0, 1, -1]
    //    emptyElValue: -1
    @Test
    void should_move_list_on_1_left() throws OSException {

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(0);
        list.add(0);
        list.add(1);

        Functions.moveLeftOn(1, list, -1);

        assertEquals(1, list.get(0));
        assertEquals(0, list.get(1));
        assertEquals(0, list.get(2));
        assertEquals(1, list.get(3));
        assertEquals(-1, list.get(4));

    }

    //    before: [1, 1, 0, 0, 1]
    //    after: [0, 0, 1, 0, 0]
    //    emptyElValue: 0
    @Test
    void should_move_list_on_2_left() throws OSException {

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(0);
        list.add(0);
        list.add(1);

        Functions.moveLeftOn(2, list, 0);

        assertEquals(0, list.get(0));
        assertEquals(0, list.get(1));
        assertEquals(1, list.get(2));
        assertEquals(0, list.get(3));
        assertEquals(0, list.get(4));

    }

    /*
    before:
     a : [1, 0, 1, 0, 0]
     b : [1, 1, 0, 0, 1]

    after:
      r : [0, 1, 1, 0, 1]
    */
    @Test
    void test_xor_function() throws OSException {

        List<Integer> a = new ArrayList<Integer>();
        a.add(1);
        a.add(0);
        a.add(1);
        a.add(0);
        a.add(0);

        List<Integer> b = new ArrayList<Integer>();
        b.add(1);
        b.add(1);
        b.add(0);
        b.add(0);
        b.add(1);


        List<Integer> result = Functions.xor(a, b);

        assertEquals(0, result.get(0));
        assertEquals(1, result.get(1));
        assertEquals(1, result.get(2));
        assertEquals(0, result.get(3));
        assertEquals(1, result.get(4));
    }

    /*
        v = 10100110000
        g = 10011
        остаток v / g = 00111
     */

    @Test
    void test_division_function_1() throws OSException {

        List<Integer> infVector = new ArrayList<>();

        infVector.add(1);
        infVector.add(0);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);

        List<Integer> gVector = new ArrayList<>();

        gVector.add(1);
        gVector.add(0);
        gVector.add(0);
        gVector.add(1);
        gVector.add(1);


        List<Integer> result = new ArrayList<>();

        result = Functions.division(infVector, gVector);

        assertEquals(0, result.get(0));
        assertEquals(0, result.get(1));
        assertEquals(1, result.get(2));
        assertEquals(1, result.get(3));
        assertEquals(1, result.get(4));

    }

    /*
        v = 10100110002
        g = 10011
        остаток v / g = 00110
     */

    @Test
    void test_division_function_2() throws OSException {

        List<Integer> infVector = new ArrayList<>();

        infVector.add(1);
        infVector.add(0);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);

        List<Integer> gVector = new ArrayList<>();

        gVector.add(1);
        gVector.add(0);
        gVector.add(0);
        gVector.add(1);
        gVector.add(1);


        List<Integer> result = new ArrayList<>();

        result = Functions.division(infVector, gVector);

        assertEquals(0, result.get(0));
        assertEquals(0, result.get(1));
        assertEquals(1, result.get(2));
        assertEquals(1, result.get(3));
        assertEquals(0, result.get(4));

    }

    @Test
    void division_test_3() throws OSException {
        List<Integer> infVector =new ArrayList<>();
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);
        infVector.add(0);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);
        infVector.add(1);

        List<Integer> infVectorMoved = new ArrayList<>(infVector);
        for(int i = 0; i < 4; i++){
            infVectorMoved.add(0);
        }

        List<Integer> gVector = new ArrayList<>();

        gVector.add(1);
        gVector.add(0);
        gVector.add(0);
        gVector.add(1);
        gVector.add(1);


        List<Integer> result = new ArrayList<>();

        result = Functions.division(infVectorMoved, gVector);
        System.out.println(result);

    }

    /*
    before:
     from : [1, 0, 1, 0, 0]
     to : [1, 1, 1, 1, 1]

    after:
      from : [1, 0, 1, 0, 0]
      to : [1, 0, 1, 0, 0]
    */
    @Test
    void copy_test() throws OSException {
        List<Integer> from = new ArrayList<Integer>();
        from.add(1);
        from.add(0);
        from.add(1);
        from.add(0);
        from.add(0);

        List<Integer> to = new ArrayList<Integer>();
        to.add(1);
        to.add(1);
        to.add(1);
        to.add(1);
        to.add(1);

        Functions.copy(from, to);

        assertEquals(1, to.get(0));
        assertEquals(0, to.get(1));
        assertEquals(1, to.get(2));
        assertEquals(0, to.get(3));
        assertEquals(0, to.get(4));
    }


    @Test
    void concat_test() throws OSException {
        List<Integer> infVector = new ArrayList<>();

        infVector.add(1);
        infVector.add(0);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);


        List<Integer> rest = new ArrayList<>();

        rest.add(0);
        rest.add(0);
        rest.add(1);
        rest.add(1);
        rest.add(1);

        Functions.concat(infVector, rest);

        assertEquals(1, infVector.get(6));
        assertEquals(0, infVector.get(7));
        assertEquals(1, infVector.get(8));
        assertEquals(1, infVector.get(9));
        assertEquals(1, infVector.get(10));

    }

    @Test
    void check_get_factorial_function(){
        assertEquals(1, Functions.getFactorial(1));
        assertEquals(24, Functions.getFactorial(4));
        assertEquals(5040, Functions.getFactorial(7));
    }

    @Test
    void check_combine_function() throws OSException {
        assertEquals(15, Functions.combine(15, 1));
        assertEquals(105, Functions.combine(15, 2));
        assertEquals(1365, Functions.combine(15, 4));

    }

    @Test
    void test_err_count(){
        List<Integer> infVector = new ArrayList<>();

        infVector.add(1);
        infVector.add(0);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);


        assertEquals(4, Functions.countErr(infVector));
    }

    @Test
    void test_sumV_and_err() throws OSException {
        List<Integer> infVector = new ArrayList<>();

        infVector.add(1);
        infVector.add(0);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);


        List<Integer> err = new ArrayList<>();

        err.add(0);
        err.add(1);
        err.add(1);
        err.add(0);
        err.add(0);
        err.add(1);
        err.add(1);
        err.add(0);
        err.add(0);
        err.add(0);
        err.add(1);

        Functions.sumVectorAndError(infVector, err);

        System.out.println(infVector);
    }

    @Test
     void check_contain1_func(){
        List<Integer> err = new ArrayList<>();

        err.add(0);
        err.add(0);
        err.add(0);
        err.add(0);
        err.add(0);
        err.add(0);
        err.add(0);
        err.add(0);
        err.add(0);
        err.add(0);
        err.add(1);

        assertTrue(Functions.contain1(err));
    }

    @Test
    void ready_program() throws OSException {
        Map<Integer, List<List<Integer>>> errorsReal = Functions.createErrors();

        System.out.println("---------/Генерация ошибок/---------");
        // подсчет ошибок каждого класса
        Map<Integer, Long> errorsNum = new HashMap<>();

        for (int i = 1; i <= 15; i++) {
            errorsNum.put(i, Functions.combine(15, i));
        }
        for (Map.Entry<Integer, Long> el : errorsNum.entrySet()) {
            System.out.println(String.format("Число ошибок класса %s: %s", el.getKey(), el.getValue()));
        }
        Long numberErrors = 0L;
        for (int i = 1; i <= 15; i++) {
            numberErrors+= errorsNum.get(i);
        }

        System.out.println();
        System.out.println(String.format("Общее число ошибок: %s", numberErrors));
        System.out.println();


        List<Integer> infVector = new ArrayList<>();
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);
        infVector.add(0);
        infVector.add(1);
        infVector.add(0);
        infVector.add(0);
        infVector.add(1);
        infVector.add(1);

        System.out.println(String.format("Информационный вектор : %s", infVector));
        System.out.println();

        //сдвигаем на 4 влево
        List<Integer> infVectorMoved = new ArrayList<>(infVector);
        for (int i = 0; i < 4; i++) {
            infVectorMoved.add(0);
        }

        System.out.println(String.format("Информационный вектор со сдвигом в лево на 4: %s", infVectorMoved));
        System.out.println();

        List<Integer> gVector = new ArrayList<>();

        gVector.add(1);
        gVector.add(0);
        gVector.add(0);
        gVector.add(1);
        gVector.add(1);



        // таблица синдромов
         Map<Integer, List<List<Integer>>> errorsReal1 = Functions.createErrors();
        Map<Integer, Map<List<Integer>, List<Integer>>> mapSindromErr = new HashMap<>();

        for (int i = 1; i <= 15; i++) {
            Map<List<Integer>, List<Integer>> mapSindErrHelp = new HashMap<>();

            List<List<Integer>> errorsVector = errorsReal1.get(i);
            for (List<Integer> err : errorsVector) {
                List<Integer> rest = Functions.division(err, gVector);
                mapSindErrHelp.put(rest, err);
            }
            mapSindromErr.put(i, mapSindErrHelp);
        }
        System.out.println("");

//        // число полученных синдромов ошибок для различных кратностей ошибок
//        System.out.println("---------/Вычисление синдромов/---------");
//        System.out.println();
//
//        for (Map.Entry<Integer, Map<List<Integer>, List<Integer>>> el : mapSindromErr.entrySet()) {
//            System.out.println(String.format("Кратность синдрома: %s", el.getKey()));
//            System.out.println(String.format("Число полученных синдромов: %s", el.getValue().size()));
//            System.out.println("");
//        }
//        System.out.println();


        System.out.println("---------/Обнаруживающая способность/---------");
        System.out.println();
        for (int i = 1; i <= 15; i++) {
            Integer findErrCount = 0;
            Integer errCount = 0;
            for (List<Integer> errors : errorsReal.get(i)) {
                List<Integer> v = new ArrayList<>(infVectorMoved);
                //System.out.println(String.format("Вектор ошибки: %s", errors));
                errCount++;

                Functions.sumVectorAndError(v, errors);

                List<Integer> restDecode = Functions.division(v, gVector);
                if (Functions.contain1(restDecode)) {
                    findErrCount++;

                    // ИСПРАВЛЯЕМ ОШИБКУ
                }

            }


            System.out.println(String.format("*** Ошибки кратности %s ***", i));
            System.out.println(String.format("Число ошибок: %s", errCount));
            System.out.println(String.format("Найдено: %s", findErrCount));
            System.out.println(String.format("Обнаруживающая способность: %.2f%%", (findErrCount.doubleValue() / errCount.doubleValue()) * 100));
            System.out.println();
        }
    }




}