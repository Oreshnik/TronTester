This is a Java tool for testing bots for CodinGame multiplayer <a href="https://www.codingame.com/multiplayer/bot-programming/tron-battle">Tron Battle</a>. 

When I was programming the bot for this game I noticed that there are some generated games where it is not possible to win. So, for more precise testing this program tests one game with all generated start positions for the testing version.

<h3>How does it works?</h3>
Compile sources. Run Tester.class with params:
<ul>
<li>Command line to start a new version of your bot process 
<li>Command line to start an old version of your bot process 
<li>Number of players (from 2 to 4)
<li>Number of games
</ul>

As example: 
<pre><code>java Tester "java -cp C:\Documents\CodinGame\TheTron\out\ Player" "java -cp C:\Documents\CodinGame\TheTron\out\ PlayerOld" 4 100
</code></pre>
For this example 400 games will be played (100 * 4), where new version of your bot will compete with 3 copy of old version, using all four generated start positions in each game.

At the end of the run you will see a result of testing. Something like:
<pre><code>Results for bot   java -cp C:\Documents\CodinGame\TheTron\out\ Player
1 place 96 (24.0 %)
2 place 87 (21.75 %)
3 place 109 (27.25 %)
4 place 108 (27.0 %)
Average place 2.5725
</code></pre>

This means that new version is slightly worse than the old one.
The result like “Average place 2.5” means that both bots are equal.

For every game you will see an output like this:
<pre><code>Game 117: (26,10)(0,12)(6,3)(4,2) position 0
frame 432; place 3, avg 2,50</code></pre>

You may use this parameters (26,10)(0,12)(6,3)(4,2) for creating manual game on the website, putting your new version on the position 0, to replay the game. 
Frame is the duration of the game, place means your new bot's rank at the final of the game, avg – average place for all games which was already played.
