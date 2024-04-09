import java.util.ArrayList;
import java.util.List;

public class Solution {
    //최소 힙 저장소
    ArrayList<Integer> minHeap = new ArrayList<>(List.of(-1));
    public int solution(int[] scoville, int K) {
        int answer = 0;
        //주어진 값 힙에 저장
        for(int s : scoville){
            enqueue(s);
        }

        //최소 값이 K보다 작으면 반복
        while(minHeap.get(1) < K){
            int first = pop();
            int second = pop();
            //2번째로 낮은 값이 없으면 종료
            if(second == -1){
                return -1;
            }

            //연산 후 다시 힙에 삽입
            int mix = first + 2 * second;
            enqueue(mix);
            answer++;
        }

        return answer;
    }

    //최소힙 삽입
    void enqueue(int item){
        //말단에 삽입
        minHeap.add(item);
        int idx = minHeap.size() - 1;
        //부모 노드보다 작으면 부모와 교체
        while (idx > 1 && minHeap.get(idx/2) > minHeap.get(idx) ){
            minHeap.set(idx, minHeap.get(idx/2));
            idx /= 2;
            minHeap.set(idx, item);
        }
    }

    //최소힙 출력 및 삭제
    int pop(){
        //데이터 없으면 -1 출력
        if(minHeap.size() == 1){
            return -1;
        }

        //최소값 가져오고 최말단 값을 root로 올림
        int minValue = minHeap.get(1);
        minHeap.set(1, minHeap.get(minHeap.size() - 1));
        minHeap.remove(minHeap.size() - 1);

        int idx = 1;
        //자식노드가 존재하는 경우까지만 반복
        while (idx * 2 < minHeap.size()){
            int leftOrRight = idx * 2;
            //오른쪽이 더 작은지 확인
            if(idx * 2 + 1 < minHeap.size() && minHeap.get(idx * 2) > minHeap.get(idx * 2 + 1)){
                leftOrRight = idx * 2 + 1;
            }

            //부모와 자식 간 교체 작업
            if(minHeap.get(idx) > minHeap.get(leftOrRight)){
                int temp = minHeap.get(idx);
                minHeap.set(idx, minHeap.get(leftOrRight));
                minHeap.set(leftOrRight, temp);
            }

            idx = leftOrRight;
        }

        return minValue;
    }
}