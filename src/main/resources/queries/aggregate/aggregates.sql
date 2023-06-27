-- statements with aggregate functions and 'group by' without 'having':

SELECT gender,
       COUNT(*)
  FROM person GROUP BY gender;

SELECT AVG(grade)
  FROM grades
 GROUP BY student_id;

SELECT gender,
       MAX(date_of_birth),
       MIN(date_of_birth)
  FROM person
 GROUP BY gender;

SELECT SUM(grade)
  FROM grades
 GROUP BY student_id;

SELECT COUNT(DISTINCT course_id)
  FROM applicant
 GROUP BY person_id;

-- statements with aggregate functions and 'group by' with 'having':

SELECT gender,
       COUNT(*)
  FROM person
 GROUP BY gender
HAVING COUNT(*) > 50;

SELECT AVG(grade)
  FROM grades
 GROUP BY student_id
HAVING AVG(grade) > 80;

SELECT gender,
       MAX(date_of_birth),
       MIN(date_of_birth)
  FROM person
 GROUP BY gender
HAVING MAX(date_of_birth) > '1990-01-01';

SELECT SUM(grade)
  FROM grades
 GROUP BY student_id
HAVING SUM(grade) > 300;

SELECT COUNT(DISTINCT course_id)
  FROM applicant
 GROUP BY person_id
HAVING COUNT(DISTINCT course_id) > 3;

SELECT AVG(grade)
  FROM grades
 GROUP BY subject_id
HAVING AVG(grade) > 90;

SELECT MAX(date_of_birth)
  FROM person
 GROUP BY gender
HAVING MAX(date_of_birth) < '1980-01-01';