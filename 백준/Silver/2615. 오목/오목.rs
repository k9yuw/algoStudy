use std::io::{self, Read};

const BOARD_SIZE: usize = 19;
const DIRECTIONS: [(isize, isize); 4] = [
    (0, 1),   // 오른쪽
    (1, 0),   // 아래쪽
    (1, 1),   // 오른쪽 아래 대각선
    (1, -1),  // 왼쪽 아래 대각선
];

fn main() {
    let mut board = [[0; BOARD_SIZE]; BOARD_SIZE];

    // 오목판 입력 받기
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();
    let mut input_tokens = input.split_whitespace();

    for r in 0..BOARD_SIZE {
        for c in 0..BOARD_SIZE {
            board[r][c] = input_tokens.next().unwrap().parse::<usize>().unwrap();
        }
    }

    // 모든 셀을 순회
    for r in 0..BOARD_SIZE {
        for c in 0..BOARD_SIZE {
            if board[r][c] != 0 {
                let color = board[r][c];
                for &(dy, dx) in &DIRECTIONS {
                    // 현재 방향의 이전 위치에 같은 색 돌이 있는지 확인 (중복탐색 방지)
                    let py = r as isize - dy;
                    let px = c as isize - dx;
                    if py >= 0 && py < BOARD_SIZE as isize && px >= 0 && px < BOARD_SIZE as isize {
                        if board[py as usize][px as usize] == color {
                            continue; 
                        }
                    }

                    // 다섯 개 연속인지 확인
                    let mut count = 1;
                    let mut ny = r as isize + dy;
                    let mut nx = c as isize + dx;

                    while ny >= 0 && ny < BOARD_SIZE as isize && nx >= 0 && nx < BOARD_SIZE as isize && board[ny as usize][nx as usize] == color {
                        count += 1;
                        if count > 5 {
                            break; // 여섯 개 이상인 경우 이긴게 아니다. 
                        }
                        ny += dy;
                        nx += dx;
                    }

                    if count == 5 {
                        // 다섯 개의 돌 다음 위치에 같은 색 돌이 있는지 확인하여 장목 방지
                        if ny >= 0 && ny < BOARD_SIZE as isize && nx >= 0 && nx < BOARD_SIZE as isize {
                            if board[ny as usize][nx as usize] == color {
                                continue; 
                            }
                        }

                        // 승리 조건을 만족한 경우
                        if dy == 1 && dx == -1 {
                            // 방향이 왼쪽 아래 대각선 (1, -1)인 경우, 가장 왼쪽에 있는 돌은 제일 끝에 위치
                            let end_r = r as isize + dy * 4;
                            let end_c = c as isize + dx * 4;
                            println!("{}", color);
                            println!("{} {}", end_r+1, end_c+1); 
                        } else {
                            // 다른 방향은 시작 위치가 가장 왼쪽 또는 가장 위쪽
                            println!("{}", color);
                            println!("{} {}", r+1, c+1);
                        }
                        return;
                    }
                }
            }
        }
    }

    // 승리 조건을 만족하지 못한 경우
    println!("0");
}
