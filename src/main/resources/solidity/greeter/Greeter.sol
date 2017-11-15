pragma solidity ^0.4.2;

// Modified Greeter contract. Based on example at https://www.ethereum.org/greeter.

contract mortal {
    /* Define variable owner of the type address*/
    address owner;

    /* this function is executed at initialization and sets the owner of the contract */
    function mortal() { owner = msg.sender; }

    /* Function to recover the funds on the contract */
    function kill() { if (msg.sender == owner) suicide(owner); }
}

contract greeter is mortal {
    /* define variable greeting of the type string */
    string greeting;

    /* this runs when the contract is executed */
    function greeter(string _greeting) public {
        greeting = _greeting;
    }

    function newGreeting(string _greeting) public {
        Modified(greeting, _greeting, greeting, _greeting);
        greeting = _greeting;
    }

    /* main function */
    function greet() constant returns (string) {
        return greeting;
    }

    /* we include indexed events to demonstrate the difference that can be
    captured versus non-indexed */
    event Modified(
            string indexed oldGreetingIdx, string indexed newGreetingIdx,
            string oldGreeting, string newGreeting);
}
