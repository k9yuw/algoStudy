use std::io::{self, Read};

struct Score {
    credit: i32,
    grade: String,
}

fn main() {
    let mut scores: Vec<Score> = Vec::new();

    // 전체 입력을 통째로 받는다.
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    // 입력을 ASCII 공백 기준으로 나눈다.
    let mut input_tokens = input.split_ascii_whitespace();

    let n: usize = input_tokens.next().unwrap().parse().unwrap(); // 과목 수
    let goal: f64 = input_tokens.next().unwrap().parse().unwrap(); // 목표 GPA

    // 목표 GPA를 100배하여 정수로 변환 (반올림)
    let goal_scaled: i64 = (goal * 100.0).round() as i64;

    // 입력된 과목들의 성적 scores 벡터에 입력
    for _ in 0..(n - 1) {
        let curr_credit: i32 = input_tokens.next().unwrap().parse().unwrap();
        let curr_grade: String = input_tokens.next().unwrap().to_string();

        let curr = Score {
            credit: curr_credit,
            grade: curr_grade,
        };

        scores.push(curr);
    }

    // x 학점의 크레딧 입력
    let x_credit: i32 = input_tokens.next().unwrap().parse().unwrap();

    // 이전 과목들의 합계 계산 (credit * grade_scaled)
    let mut sum_before_x: i64 = 0;
    let mut total_credit_before_x: i64 = 0;

    for score in &scores {
        let grade_scaled = grade_to_num_scaled(&score.grade);
        sum_before_x += (score.credit as i64) * grade_scaled;
        total_credit_before_x += score.credit as i64;
    }

    let total_credit: i64 = total_credit_before_x + (x_credit as i64);

    // 가능한 성적을 낮은 순서부터 정의 (100배 된 값 사용)
    let scale = vec![
        ("F", 0),
        ("D0", 100),
        ("D+", 150),
        ("C0", 200),
        ("C+", 250),
        ("B0", 300),
        ("B+", 350),
        ("A0", 400),
        ("A+", 450),
    ];

    let mut result = "impossible".to_string();

    for (grade_str, grade_num_scaled) in scale {
        // 이번 학기에 이 성적을 받을 경우의 총 평점 합계
        let sum_with_x = sum_before_x + (x_credit as i64) * grade_num_scaled;

        // 평균 평점 계산 (100배 스케일된 값)
        let avg_gpa = sum_with_x / total_credit;

        // 목표 GPA와 비교 (100배 스케일)
        if avg_gpa > goal_scaled {
            result = grade_str.to_string();
            break;
        }
    }

    println!("{}", result);
}

// 문자로 된 성적을 100배가 된 숫자값으로 변환해주는 함수
fn grade_to_num_scaled(grade: &str) -> i64 {
    match grade {
        "A+" => 450,
        "A0" => 400,
        "B+" => 350,
        "B0" => 300,
        "C+" => 250,
        "C0" => 200,
        "D+" => 150,
        "D0" => 100,
        "F"  => 0,
        _    => 0,
    }
}
