# Parking System
Parking lot suggestion system

# Description
This system is Java console application. <br />
The parking system offers the driver the nearest parking floor. <br />
Parking can accommodate three types of vehicles: vans, cars with petrol/diesel engines and electric cars. <br />
Each vehicle can enter the parking lot from any floor.
The system offers the driver the nearest parking space and adds this parking space to the list of occupied parking spaces. <br/>
In this case, the parking system has 10 floors, 2 underground and 8 ordinary floors. <br />
Underground floors only for vans. <br />
Two upper floors for electric cars only. <br />
Other floors for cars with petrol/diesel engines, but if all floors occupied, cars with petrol/diesel engines
can use all other free floors for parking.

# How to use
1) Run program. <br />
2) Enter vehicle entry floor. <br />
3) Enter vehicle type (petrol/diesel car, electric car or van). <br />
4) The parking system will give you the recommended floor for parking vehicle by type. <br />
5) The system shows parking occupancy level on each floor. <br />

# Testing
A simple dummy data for testing: <br />
Explanation: <br />
the first number vehicle entry floor <br />
the second number vehicle type (1 - petrol diesel, 2 - van, 3 - electric car) <br />
the third number is the expected output of the system <br />
1) -2 2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output: -2 <br />
2) 8 1  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output:  6 <br />
3) 3 3  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output:  7 <br />
4) 1 1  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output:  1
5) -2 1 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output:  1
6) -1 1 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output:  1
7) -2 1 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output:  1
8) 1 1  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output:  2
9) 6 3  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output:  7
10) -2 3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output: 7
11) 8 1  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output: 6
12) 4 1  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output: 4
13) 3 2  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output:-1
14) 5 3  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output: 7
15) 2 3  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Output: 8
