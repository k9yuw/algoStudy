use std::io::{self, Read};

struct Stack {
    stack: Vec<i64>,
}

impl Stack {
    fn new() -> Stack {
        Stack { stack: Vec::new() }
    }

    fn validate_and_push(&mut self, value: i64) -> bool {
        if self.stack.len() >= 1000 || value.abs() > 1_000_000_000 {
            return false;
        }
        self.stack.push(value);
        true
    }

    fn pop(&mut self) -> bool {
        self.stack.pop().is_some()
    }

    fn inv(&mut self) -> bool {
        if let Some(top) = self.stack.last_mut() {
            *top = -*top;
            true
        } else {
            false
        }
    }

    fn dup(&mut self) -> bool {
        if let Some(&top) = self.stack.last() {
            self.stack.push(top);
            true
        } else {
            false
        }
    }

    fn swp(&mut self) -> bool {
        if self.stack.len() < 2 {
            false
        } else {
            let len = self.stack.len();
            self.stack.swap(len - 1, len - 2);
            true
        }
    }

    fn add(&mut self) -> bool {
        if self.stack.len() < 2 {
            false
        } else {
            let a = self.stack.pop().unwrap();
            let b = self.stack.pop().unwrap();
            self.validate_and_push(a + b)
        }
    }

    fn sub(&mut self) -> bool {
        if self.stack.len() < 2 {
            false
        } else {
            let a = self.stack.pop().unwrap();
            let b = self.stack.pop().unwrap();
            self.validate_and_push(b - a)
        }
    }

    fn mul(&mut self) -> bool {
        if self.stack.len() < 2 {
            false
        } else {
            let a = self.stack.pop().unwrap();
            let b = self.stack.pop().unwrap();
            self.validate_and_push(a * b)
        }
    }

    fn div(&mut self) -> bool {
        if self.stack.len() < 2 {
            return false;
        }
        let a = self.stack.pop().unwrap(); // Divisor
        let b = self.stack.pop().unwrap(); // Dividend
        if a == 0 {
            return false;
        }
        let result = (b.abs() / a.abs()) * if (b < 0) ^ (a < 0) { -1 } else { 1 };
        self.validate_and_push(result)
    }

    fn modulus(&mut self) -> bool {
        if self.stack.len() < 2 {
            return false;
        }
        let a = self.stack.pop().unwrap(); // Divisor
        let b = self.stack.pop().unwrap(); // Dividend
        if a == 0 {
            return false;
        }
        let result = (b.abs() % a.abs()) * if b < 0 { -1 } else { 1 };
        self.validate_and_push(result)
    }

    fn size(&self) -> usize {
        self.stack.len()
    }

    fn peek(&self) -> Option<&i64> {
        self.stack.last()
    }

    fn clear(&mut self) {
        self.stack.clear();
    }
}

fn calculator(command_list: &[String], input_numbers: &[i64], stack: &mut Stack) {
    for &i in input_numbers {
        stack.clear();
        if !stack.validate_and_push(i) {
            println!("ERROR");
            continue;
        }

        let mut error_occurred = false;

        for command in command_list {
            let tokens: Vec<&str> = command.split_whitespace().collect();
            if tokens.is_empty() {
                continue;
            }

            let is_successful = match tokens[0] {
                "NUM" => process_num_command(&tokens, stack),
                "POP" => stack.pop(),
                "INV" => stack.inv(),
                "DUP" => stack.dup(),
                "SWP" => stack.swp(),
                "ADD" => stack.add(),
                "SUB" => stack.sub(),
                "MUL" => stack.mul(),
                "DIV" => stack.div(),
                "MOD" => stack.modulus(),
                _ => false,
            };

            if !is_successful {
                println!("ERROR");
                error_occurred = true;
                break;
            }
        }

        if !error_occurred {
            if stack.size() == 1 {
                println!("{}", stack.peek().unwrap());
            } else {
                println!("ERROR");
            }
        }
    }
}

fn process_num_command(parts: &[&str], stack: &mut Stack) -> bool {
    if parts.len() != 2 {
        return false;
    }
    if let Ok(num) = parts[1].parse::<i64>() {
        stack.validate_and_push(num)
    } else {
        false
    }
}

fn main() {
    let mut stack = Stack::new();
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();
    let mut lines = input.lines();

    loop {
        let mut command_list: Vec<String> = Vec::new();
        let mut input_numbers: Vec<i64> = Vec::new();

        loop {
            let line = match lines.next() {
                Some(line) => line,
                None => return,
            };

            if line == "END" {
                break;
            } else if line == "QUIT" {
                return;
            } else if line.trim().is_empty() {
                continue;
            }
            command_list.push(line.to_string());
        }

        let n_line = match lines.next() {
            Some(line) => line,
            None => break,
        };
        let n: usize = n_line.parse().unwrap();

        for _ in 0..n {
            let num_line = match lines.next() {
                Some(line) => line,
                None => break,
            };
            let num: i64 = num_line.parse().unwrap();
            input_numbers.push(num);
        }

        calculator(&command_list, &input_numbers, &mut stack);

        println!();
    }
}
