# quiz03 feedback for LUA ZHI ZHAN
### 1. Running an app
- ensure ids are correct
- test PASSED.
### 2. Checking recyclerView is implemented
- Check recyclerView's classname has RecyclerView
- test PASSED.
### 3. Checking the alignment
- get the screen rect of chat and response
- check their left coordinates are separated by 50
- test PASSED.
### 4. Checking when hitting the SEND button
- enter space and send and check if it is added to chat
- enter and send 1-10, and check if chatInput is empty
- check if response 10 is displayed
- test PASSED.
### 5. Checking if a chat msg is inserted
- type "hi" and send
- check "hi" is displayed
- relaunch the app and check if "hi" is displayed
- test PASSED.
### 6. Checking if a chat msg is copied
- type "hi" and send
- click on "hi"
- check if "hi" is displayed in chatInput
- test PASSED.
### 7. Checking if a chat msg is inserted
- enter and send 11-15, and relaunch the app
- check if 15 is displayed and 11-15 can be scrollTo
- test PASSED.
### 8. Checking if high-order function can be invoked
- enter "fun(fib,10)", send
- check if "fun(fib,10) is 55" is displayed can be scrollTo
- test PASSED.
### 9. Checking if high-order function is not blocking
- enter "fun(self,20)", send
- enter "hi", send
- check "hi" is displayed
- test PASSED.
### 10. Checking if high-order function result will be updated
- enter "fun(self,10)", send
- check "fun(self,10) is ..." is displayed
- wait for 10
- check "fun(self,10) is 10" is displayed
- test PASSED.

**Your functional testing score for quiz03 is 10/10.**

### Note that there are still 2 non-func testing marks:
- Modular code design / naming conventions (1 mark)
- Well-commented code (1 mark)

** Your non-functional testing score for quiz02 is 2/2. **

** Your overall score for quiz02 is 12/12. ** 