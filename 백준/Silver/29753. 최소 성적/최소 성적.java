import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    // 성적과 대응되는 평점 맵
    private static final Map<String, BigDecimal> gradeToPoint = Map.of(
            "A+", new BigDecimal("4.5"),
            "A0", new BigDecimal("4.0"),
            "B+", new BigDecimal("3.5"),
            "B0", new BigDecimal("3.0"),
            "C+", new BigDecimal("2.5"),
            "C0", new BigDecimal("2.0"),
            "D+", new BigDecimal("1.5"),
            "D0", new BigDecimal("1.0"),
            "F", new BigDecimal("0.0")
    );

    // 가능한 성적 리스트
    private static final String[] grades = {
            "F", "D0", "D+", "C0", "C+", "B0", "B+", "A0", "A+"
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 줄 입력
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]); // 과목 수
        BigDecimal goalGPA = new BigDecimal(firstLine[1]);
        
        // 이전 과목들의 성적 합계와 총 학점
        BigDecimal sumBeforeX = BigDecimal.ZERO;
        int totalCreditBeforeX = 0;

        // N-1개의 과목에 대한 입력 처리
        for (int i = 0; i < n - 1; i++) {
            String[] line = br.readLine().split(" ");
            int credit = Integer.parseInt(line[0]);
            String grade = line[1];
            sumBeforeX = sumBeforeX.add(BigDecimal.valueOf(credit).multiply(gradeToPoint.get(grade)));
            totalCreditBeforeX += credit;
        }

        // 마지막 과목의 학점 입력
        int xCredit = Integer.parseInt(br.readLine());
        int totalCredit = totalCreditBeforeX + xCredit;

        String result = "impossible";

        // 가능한 성적을 낮은 순서부터 체크
        for (String grade : grades) {
            BigDecimal gradePoint = gradeToPoint.get(grade);
            BigDecimal sumWithX = sumBeforeX.add(BigDecimal.valueOf(xCredit).multiply(gradePoint));
            BigDecimal finalGPA = sumWithX.divide(BigDecimal.valueOf(totalCredit), 10, RoundingMode.DOWN); // 충분한 소수점 자리수 유지

            // 소수점 둘째 자리에서 버림
            BigDecimal truncatedGPA = finalGPA.setScale(2, RoundingMode.DOWN);

            // 목표 GPA를 초과하는지 확인
            if (truncatedGPA.compareTo(goalGPA) > 0) {
                result = grade;
                break;
            }
        }

        System.out.println(result);
    }
}
