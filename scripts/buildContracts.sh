#!/usr/bin/env bash

set -e
set -o pipefail

baseDir="../src/main/solidity"

targets="
greeter/Greeter
"

for target in ${targets}; do
    dirName=$(dirname "${target}")
    fileName=$(basename "${target}")

    cd $baseDir
    echo -n "Compiling Solidity file ${target}.sol"

    solc --bin --abi --optimize --overwrite \
            ${dirName}/${fileName}.sol -o ${dirName}/build
    printf "%b" "\e[1;32m\tComplete  \u2714\n\e[0m"
    echo -n "Generating contract bindings"
    web3j solidity generate \
        ${dirName}/build/${fileName}.bin \
        ${dirName}/build/${fileName}.abi \
        -p org.web3j.sample.contracts.generated \
        -o ../../main/java/ > /dev/null
    printf "%b" "\e[1;32m\t\t\tComplete  \u2714\n\e[0m"

    cd - 2>&1 >/dev/null
done
