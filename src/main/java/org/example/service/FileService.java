package org.example.service;

import com.alibaba.excel.EasyExcel;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;

@Service
public class FileService {


    // Находит N-е максимальное число в файле с использованием EasyExcel.

    public Integer findNthMaxNumber(String filePath, int n) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);

        // Используем EasyExcel для чтения файла
        EasyExcel.read(filePath, NumberData.class, new NumberDataListener(minHeap, n)).sheet().doRead();

        return minHeap.size() == n ? minHeap.peek() : null;
    }


     //Класс для маппинга данных из Excel.

    public static class NumberData {
        private Integer number;

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }


     //Слушатель для обработки данных из Excel.

    private static class NumberDataListener implements com.alibaba.excel.read.listener.ReadListener<NumberData> {
        private final PriorityQueue<Integer> minHeap;
        private final int n;

        public NumberDataListener(PriorityQueue<Integer> minHeap, int n) {
            this.minHeap = minHeap;
            this.n = n;
        }

        @Override
        public void invoke(NumberData data, com.alibaba.excel.context.AnalysisContext context) {
            if (data.getNumber() != null) {
                int value = data.getNumber();

                if (minHeap.size() < n) {
                    minHeap.offer(value);
                } else if (value > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(value);
                }
            }
        }

        @Override
        public void doAfterAllAnalysed(com.alibaba.excel.context.AnalysisContext context) {
            // Ничего не делаем после завершения чтения
        }
    }
}