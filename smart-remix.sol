pragma solidity ^0.4.16;
contract HelloWorld {
   uint256 counter = 5;
   
   function add() public {  //カウンターに1足す
       counter++;
   }
 
   function subtract() public { //カウンターから1引く
       counter--;
   }
   
   function getCounter() public constant returns (uint256) {
       return counter;
   } 

}
