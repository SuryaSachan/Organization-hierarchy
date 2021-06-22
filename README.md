# Organization-hierarchy
Problem Statement:

We want to maintain the list of employees in a company. We will be concerned with two 
quantities associated with each employee in the company -- ID of the employee (you can assume no two employees in the 
company have the same ID), and the level of the employee. The level denotes where the person stands in the hierarchy. 
Level 1 denotes the highest post in the company (say the owner), level 2 comes below level 1 and so on. There is only 1 
person at level 1, but there can be several employees at level i > 1. Each level i employee works under a level i-1 employee, 
which is his/her immediate boss. Given an employee A, we can form a sequence of employees A',A'', A''', ... where A works 
under A', A' works under A'', and so on. We say that each employee in A',A'', A''',... is a boss of A

