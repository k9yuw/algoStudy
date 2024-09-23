use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let nums: Vec<i32> = input
        .trim()
        .split_whitespace()
        .map(|num| num.parse().unwrap())
        .collect();

    if nums[0] % nums[1] == 0 {
        println!("Yes");
    } else {
        println!("No");
    }
}