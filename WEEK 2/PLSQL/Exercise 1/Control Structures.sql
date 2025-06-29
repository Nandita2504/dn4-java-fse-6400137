SET SERVEROUTPUT ON;

-- 1. Create Customers table
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    IsVIP VARCHAR2(5)
);

-- 2. Create Loans table
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    InterestRate NUMBER,
    DueDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- 3. Insert sample data
INSERT INTO Customers VALUES (1, 'Alice', TO_DATE('1950-01-01', 'YYYY-MM-DD'), 12000, 'FALSE');
INSERT INTO Customers VALUES (2, 'Bob', TO_DATE('1985-05-20', 'YYYY-MM-DD'), 8000, 'FALSE');
INSERT INTO Customers VALUES (3, 'Charlie', TO_DATE('1945-03-10', 'YYYY-MM-DD'), 15000, 'FALSE');

INSERT INTO Loans VALUES (101, 1, 8.5, SYSDATE + 20); -- Alice
INSERT INTO Loans VALUES (102, 2, 7.0, SYSDATE + 40); -- Bob
INSERT INTO Loans VALUES (103, 3, 9.0, SYSDATE + 10); -- Charlie

COMMIT;

-- 4. Scenario 1: Apply 1% discount for age > 60
BEGIN
    FOR cust_rec IN (
        SELECT c.CustomerID, l.LoanID, l.InterestRate, 
               FLOOR(MONTHS_BETWEEN(SYSDATE, c.DOB) / 12) AS Age
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID
    )
    LOOP
        IF cust_rec.Age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = cust_rec.LoanID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- 5. Scenario 2: Mark IsVIP = TRUE for balance > 10000
BEGIN
    FOR cust_rec IN (SELECT CustomerID, Balance FROM Customers)
    LOOP
        IF cust_rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust_rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- 6. Scenario 3: Print reminders for loans due in next 30 days
BEGIN
    FOR loan_rec IN (
        SELECT l.LoanID, c.Name, l.DueDate
        FROM Loans l
        JOIN Customers c ON c.CustomerID = l.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE AND SYSDATE + 30
    )
    LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || loan_rec.Name ||
                             ', your loan (Loan ID: ' || loan_rec.LoanID ||
                             ') is due on ' || TO_CHAR(loan_rec.DueDate, 'DD-MON-YYYY'));
    END LOOP;
END;
/

-- 7. Check results
SELECT * FROM Customers;
SELECT * FROM Loans;
