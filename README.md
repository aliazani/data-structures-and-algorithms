# data-structures-and-algorithms

## Linear Data Structures

| Arrays    | Linked List (Singly, Doubly) | Stack (Using Array, Linked List, Queue) | Queue (Using Array, Linked List, Stack) (Priority Queue(Using Array, Heap)) | Hash Tables (Open Addressing (Linear Probing, Double Hashing), Chaining) | 
|-----------|------------------------------|-----------------------------------------|-----------------------------------------------------------------------------|--------------------------------------------------------------------------|
| insert    | addFirst                     | push                                    | enqueue                                                                     | put                                                                      | 
| insertAt  | addLast                      | pop                                     | dequeue                                                                     | get                                                                      | 
| removeAt  | add                          | peek                                    | peek                                                                        | remove                                                                   | 
| indexOf   | deleteFirst                  | isEmpty                                 | size                                                                        | size                                                                     | 
| get       | deleteLast                   | size                                    | isEmpty                                                                     | isEmpty                                                                  | 
| max       | delete                       | N/A                                     | N/A                                                                         | N/A                                                                      | 
| intersect | indexOf                      | N/A                                     | N/A                                                                         | N/A                                                                      | 
| size      | contains                     | N/A                                     | N/A                                                                         | N/A                                                                      | 
| reverse   | size                         | N/A                                     | N/A                                                                         | N/A                                                                      | 
| N/A       | hasLoop                      | N/A                                     | N/A                                                                         | N/A                                                                      | 
| N/A       | toArray                      | N/A                                     | N/A                                                                         | N/A                                                                      | 
| N/A       | reverse                      | N/A                                     | N/A                                                                         | N/A                                                                      | 
| N/A       | getKthFromEnd                | N/A                                     | N/A                                                                         | N/A                                                                      | 
| N/A       | getMiddle                    | N/A                                     | N/A                                                                         | N/A                                                                      | 
| N/A       | getNode / getNodeValue       | N/A                                     | N/A                                                                         | N/A                                                                      | 

## Non-Linear Data Structures

| Binary Tree        | AVL Tree      | Heap (Min, Max) | Trie (Using Array, HashMap) | Directed Graph       | Undirected  Graph             |
|--------------------|---------------|-----------------|-----------------------------|----------------------|-------------------------------|
| insert             | size          | insert          | insert                      | addVertex            | addVertex                     |
| remove             | insert        | remove          | contains                    | removeVertex         | addEdge                       |
| contains           | representTree | get             | contains2                   | addEdge              | getShortestPath (Dijkstra)    |
| contains2          | N/A           | isFull          | preOrderTraverse            | removeEdge           | hasCycle                      |
| traverseLevelOrder | N/A           | isEmpty         | postOrderTraverse           | findNeighbours       | getMinimumSpanningTree (Prim) |
| traversePreOrder   | N/A           | size            | remove                      | queryEdge            | N/A                           |
| traverseInOrder    | N/A           | N/A             | findWords                   | traverseDepthFirst   | N/A                           |
| traversePostOrder  | N/A           | N/A             | countWords                  | traverseDepthFirst2  | N/A                           |
| height             | N/A           | N/A             | longestCommonPrefix         | traverseBreadthFirst | N/A                           |
| min                | N/A           | N/A             | N/A                         | topologicalSort      | N/A                           |
| max                | N/A           | N/A             | N/A                         | hasCycle             | N/A                           |
| equalsTree         | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| isBinarySearchTree | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| swapRootChildren   | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| nodeAtDistance     | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| isBalanced         | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| isPerfect          | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| size               | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| size2              | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| countLeaves        | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| areSibling         | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| getAncestors       | N/A           | N/A             | N/A                         | N/A                  | N/A                           |
| representTree      | N/A           | N/A             | N/A                         | N/A                  | N/A                           |

## Algorithms

| Soring Algorithms | Searching Algorithms | String Manipulation       |
|-------------------|----------------------|---------------------------|
| Bubble Sort       | Linear Search        | countVowels               |
| Selection Sort    | Binary Search        | reverse                   |
| Insertion Sort    | Ternary Search       | reverseWords              |
| Merge Sort        | Jump Search          | areRotations              |
| Quick Sort        | Exponential Search   | removeDuplicates          |
| Counting Sort     | N/A                  | findMostRepeatedCharacter |
| Bucket Sort       | N/A                  | capitalize                |
| N/A               | N/A                  | areAnagrams               |
| N/A               | N/A                  | areAnagrams2              |
| N/A               | N/A                  | isPalindrome              |
| N/A               | N/A                  | N/A                       |
| N/A               | N/A                  | N/A                       |
| N/A               | N/A                  | N/A                       |
| N/A               | N/A                  | N/A                       |
| N/A               | N/A                  | N/A                       |