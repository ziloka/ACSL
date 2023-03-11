use std::{
    fmt::Display,
    fs::{read_to_string, File},
    io::Write,
};

fn main() -> Result<(), core::num::ParseIntError> {
    match read_to_string("input.txt") {
        Ok(contents) => {
            let mut output = String::new();
            for line in contents.split('\n') {
                let mut iter = line.split(' ');
                let num_rows = iter.next().unwrap().parse::<usize>()?;
                let num_cols = iter.next().unwrap().parse::<usize>()?;
                let starting_cell_number = iter.next().unwrap().parse::<usize>()?;
                let n_blocked_cells = iter.next().unwrap().parse::<usize>()?;
                let mut blocked_cells = Vec::new();
                for _ in 0..n_blocked_cells {
                    blocked_cells.push(iter.next().unwrap().parse::<usize>()?);
                }
                let mut grid = Grid::new(num_rows, num_cols, blocked_cells);
                let string = grid.stretch(starting_cell_number);
                println!("{}", string);
                output.push_str((string + "\n").as_str());
            }
            let output_file = File::create("output.txt");
            match output_file {
                Ok(mut file) => match file.write_all(output.as_bytes()) {
                    Ok(_) => println!("Wrote to output.txt"),
                    Err(e) => println!("Error: {}", e),
                },
                Err(e) => println!("Error: {}", e),
            }
        }
        Err(e) => println!("Error: {}", e),
    }

    Ok(())
}

#[derive(Clone, Copy, Debug)]
enum SquareType {
    Obstacle,
    Piece(Tile),
}

#[derive(Debug)]
struct Grid {
    pub grid: Vec<Vec<Option<SquareType>>>,
    width: usize,
    height: usize,
}

impl Grid {
    fn new(height: usize, width: usize, blocked_cells: Vec<usize>) -> Self {
        let mut grid = Self {
            grid: vec![vec![None; width]; height],
            width,
            height,
        };
        for cell in blocked_cells {
            let (row, col) = grid.cell_number_to_index(cell);
            grid.grid[row][col] = Some(SquareType::Obstacle);
        }
        grid
    }

    // output is always left to right
    fn stretch(&mut self, starting_pos: usize) -> String {
        let mut result = String::new();
        // determine start on left or right side of grid
        let left_to_right = starting_pos % self.width == 1;
        let mut current_piece = Block::A;
        let mut left_off = self.cell_number_to_index(starting_pos);
        while self
            .get_column_elements(if left_to_right { self.width - 1 } else { 0 })
            .iter()
            .all(|x| x.is_none())
        {
            let bricks = current_piece.get_structure(left_off, left_to_right);
            if !self.conflict(&bricks) {
                self.place_piece(&bricks, left_to_right, &mut left_off);
                result.push_str(format!("{:?}", current_piece).as_str());
            }
            current_piece = current_piece.next_piece();
        }

        if !left_to_right {
            result = result.chars().rev().collect();
        }

        result
    }

    fn place_piece(
        &mut self,
        bricks: &Vec<Tile>,
        left_to_right: bool,
        left_off: &mut (usize, usize),
    ) {
        bricks.iter().enumerate().for_each(|(i, tile)| {
            match tile {
                Tile::Filled(row, col) => {
                    self.grid[*row][*col] = Some(SquareType::Piece(Tile::Filled(*row, *col)));
                }
                Tile::Circle(row, col) => {
                    self.grid[*row][*col] = Some(SquareType::Piece(Tile::Circle(*row, *col)));
                    if bricks.len() - 1 == i {
                        if left_to_right {
                            *left_off = (*row, *col + 1)
                        } else {
                            *left_off = (*row, col.wrapping_sub(1));
                        }
                    }
                }
            }
        });
    }

    fn conflict(&self, positions: &Vec<Tile>) -> bool {
        for brick in positions {
            match brick {
                Tile::Filled(row, col) => {
                    if *row >= self.height || *col >= self.width || self.grid[*row][*col].is_some()
                    {
                        return true;
                    }
                }
                Tile::Circle(row, col) => {
                    if *row >= self.height || *col >= self.width || self.grid[*row][*col].is_some()
                    {
                        return true;
                    }
                }
            }
        }
        false
    }

    fn get_column_elements(&self, num: usize) -> Vec<Option<SquareType>> {
        let mut result = Vec::new();
        for row in &self.grid {
            result.push(row[num]);
        }
        result
    }

    // get row and col for cell pos
    fn cell_number_to_index(&self, num: usize) -> (usize, usize) {
        if num % self.width == 0 {
            ((num / self.width) - 1, self.width - 1)
        } else {
            (num / self.width, (num % self.width) - 1)
        }
    }

    fn index_to_cell_number(&self, row: usize, col: usize) -> usize {
        row * self.width + col
    }
}

impl Display for Grid {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        for row in &self.grid {
            for cell in row {
                match cell {
                    Some(_) => write!(f, "X")?,
                    None => write!(f, "O")?,
                }
            }
            writeln!(f)?;
        }
        write!(f, "")
    }
}

#[derive(Clone, Copy, Debug)]
enum Tile {
    Filled(usize, usize),
    Circle(usize, usize),
}

#[derive(Clone, Debug)]
enum Block {
    A,
    B,
    C,
}

impl Block {
    /// this is for the structure when going left to right
    fn get_structure(&self, left_off: (usize, usize), left_to_right: bool) -> Vec<Tile> {
        let structure: Vec<Tile>;

        if left_to_right {
            structure = match self {
                Block::A => vec![Tile::Circle(0, 0), Tile::Filled(0, 1), Tile::Circle(0, 2)],
                Block::B => vec![Tile::Circle(0, 0), Tile::Filled(1, 0), Tile::Circle(1, 1)],
                Block::C => vec![
                    Tile::Circle(0, 0),
                    Tile::Filled(0, 1),
                    Tile::Filled(1, 1),
                    Tile::Circle(2, 1),
                ],
            };
        } else {
            structure = match self {
                Block::A => vec![Tile::Circle(0, 0), Tile::Filled(0, 1), Tile::Circle(0, 2)],
                Block::B => vec![Tile::Circle(0, 0), Tile::Filled(0, 1), Tile::Circle(1, 1)],
                Block::C => vec![
                    Tile::Circle(0, 0),
                    Tile::Filled(1, 0),
                    Tile::Filled(2, 0),
                    Tile::Circle(2, 1),
                ],
            };
        }

        if left_to_right {
            structure
                .iter()
                .map(|x| match x {
                    Tile::Filled(row, col) => Tile::Filled(left_off.0 + *row, left_off.1 + *col),
                    Tile::Circle(row, col) => Tile::Circle(left_off.0 + *row, left_off.1 + *col),
                })
                .collect::<Vec<_>>()
        } else {
            structure
                .iter()
                .map(|x| match x {
                    Tile::Filled(row, col) => Tile::Filled(left_off.0.wrapping_sub(*row), left_off.1.wrapping_sub(*col)),
                    Tile::Circle(row, col) => Tile::Circle(left_off.0.wrapping_sub(*row), left_off.1.wrapping_sub(*col)),
                })
                .collect::<Vec<_>>()
        }
    }

    fn next_piece(&self) -> Block {
        match self {
            Block::A => Block::B,
            Block::B => Block::C,
            Block::C => Block::A,
        }
    }
}
