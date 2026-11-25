AI acknowledge
AI tool  Claude
1. What is an interface in Java, and how is it different from a class?
2. Can an interface be used as a variable, parameter, or return type?
   What does that mean?
3. Can an interface "receive a method" as an argument? Why or why not?
4. What does it mean to pass an object that implements an interface
   to an interface-typed parameter?
5. Can one interface extend another interface?
   What does the child interface inherit?
6. If Enemy extends RenderableGroup, does an Enemy object have
   a render() method?
7. How do methods from parent interfaces become available through inheritance?
8. What is the difference between "interface extends interface"
   and "class implements interface"?
9. Can a GameState reference hold a FireWallState object? Why?
10. Why can't you pass game (a GameState) to a constructor that expects
    a PathFinder?
11. How do you get the Home tile's coordinates?
12. What is the difference between .screen() and .grid() on a Position?
13. What does World.getDestination() return, and how is it implemented?
14. What is a PathingMap, and which methods does it provide?
15. What does ScreenPosition.distance(Position) return?
16. What is the difference between a GridPosition and a ScreenPosition?
17. Which methods does Entity provide, such as setSprite(),
    setRenderOrder(), and markForRemoval()?
18. What is the difference between FixedTimer and RepeatingTimer?
19. How do you reset a FixedTimer?
    Hint: You cannot call a reset method.
20. What does GridPosition.overlaps(Position) do?
21. How does WorldBuilder.fromString() turn a map string into a list of tiles?
22. What do the map characters f, g, d, e, and s mean?
23. What happens if a map contains an unknown symbol?
24. How does WorldBuilder.fromTiles() build a World from a list of tiles?
25. What is the role of TileFactory.fromSymbol()?
26. How does generateMilliesWorld() build the default map?
27. Why should WorldBuilder use cellsPerRow() or DEFAULT_TILES_PER_ROW
    instead of hardcoding 32?
28. To detect a "wrong number of rows" error, should the condition use
    == or !=?
29. How does a Spawner spawn enemies?
    What timer and path finder does it use?
30. What PathFinder does a Knight use, and how does it choose its direction
    during each tick?
31. Why must a Knight snap to the grid only when its direction changes,
    rather than during every tick?
32. How does a Knight switch its sprite based on its direction?
33. What does EnemyManager do?
    Consider spawn, tick, render, cleanup, and getNearest().
34. What should FireWallState.getAllEnemies() return?
35. What should FireWallState.spawnEnemy() do?
36. How does FireWallState.checkGameOver() detect that the game is over?
37. Why must checkGameOver() handle the case where the nearest enemy is null?
38. How does a Tower place bombs and tick them?
39. Why must a Tower call setSprite() instead of assigning a private field?
40. Why must a Tower reset its timer inside if (timer.isFinished())?
41. What are DEFAULT_RANGE and DEFAULT_FIRERATE used for in Tower?
42. How does a Bomb find its target?
    Which path finder does it use?
43. Why does a Bomb need to call setRenderOrder(2)?
44. How does a Tower render its bombs using list.addAll(...)?
45. How does a Grass tile detect a left click?
    Which MouseState method does it use?
46. What is the difference between isLeftPressed() and a "clicked once" event?
47. Why must you pass mouseState.screenPosition() to click(),
    rather than screen()?
48. Why did placing a tower cause many towers to be placed at once?
49. Where should the tower be placed: at screen() or mousePosition?
    Why?
50. What is the semicolon bug in if (...); and what does it cause?
51. What causes a NullPointerException with the message
    "renderables is null"?
52. What does the engine render loop do?
    Consider: tick → render → sort by render order → draw.
53. Why does String map = "brightholme.map" not work?
    What does map actually store?
54. How does Main read the map file?
55. What causes UnsupportedClassVersionError, such as class-file
    version 69 versus version 65?
56. How do class-file versions correspond to Java versions?
    For example, 65 = Java 21, 66 = Java 22, and so on.
57. Why does text.split("\n") on a 16-line file return 16 elements,
    rather than 17?
58. When would split() return 17 elements?
    Consider a trailing newline and split("\n", -1).
59. Why does a mismatch between the JDK compilation version and runtime
    version prevent the game from running?
60. How do you fix a JDK version mismatch?
    Consider the project SDK, language level, and compiler target.
61. Check the test that is important but I leaked.