# quiz02 feedback for LUA ZHI ZHAN

The program cannot compile 

Need to add a dependency in build.gradle:
implementation 'com.google.code.gson:gson:2.8.2'

After fix this, the grading result is:

### 1. Running an app
- ensure ids are correct
- test PASSED.
### 2. Checking studentId invalidity
- enter studentId "123456"
- check if "Invalid Id" is displayed
- enter studentId "123456A"
- check if "Invalid Id" is displayed
- test PASSED.
### 3. Checking RecyclerView is implemented
- scrollTo "Google"
- test PASSED.
### 4. Checking selecting on the left job list
- scrollTo position 6, click
- scrollTo position 7, click
- scrollTo position 8, click
- check the right list
- test PASSED.
### 5. Checking if 3 Jobs can be selected and send
- enter 1234567 as studentId
- on recyclerviewleft
- scrollTo position 3, click
- scrollTo position 6, click
- scrollTo position 5, click
- click send, check if "OK" is displayed
- test PASSED.
### 6. Checking if one selected job can be deleted
- on recyclerviewleft
- scrollTo position 3, click
- on recyclerviewright
- scroll To position 0, click
- check if the job is deleted
- test PASSED.
### 7. Checking if drag and drop can reorder the jobs
- on recyclerviewleft
- scrollTo position 3, click
- scrollTo position 6, click
- scrollTo position 5, click
- on recyclerviewright
- scrollTo position 0, drag to position 2
- check if the job is dragged to position 2
- FAILED -> feature not implemented
### 8. Checking clear button
- on recyclerviewleft
- scrollTo position 3, click
- hit clear
- on recyclerviewrigh
- check if there are no items
- test PASSED.
### 9. Checking the sort
- hit headerCompany
- check if the first item contains "A" or "Z"
- test PASSED.
### 10. Checking relaunch
- scrollTo position 4, click jobOption, click on 1
- pressRecentApps, swipUp, then relaunch the app
- check if jobSelection1 is still the same
- test PASSED.
### 11. Running a foreground service
- pressHome
- check that a foreground service with notification "IWSP" is running on launch app
- FAILED.
### 12. Running a foreground service
- pressHome
- check that a foreground service with notification "IWSP" is running on launch app
- click the notification to resume the activity
- click no service is running
- FAILED

As you have forgot the dependency, need to deduct 2 point from you.

**Your functional testing score for quiz02 is 7/12.**

### Note that there are still 2 non-func testing marks:
- Modular code design / naming conventions (1 mark)
- Well-commented code (1 mark)

** Your non-functional testing score for quiz02 is 2/2. **

** Your overall score for quiz02 is 9/14. ** 