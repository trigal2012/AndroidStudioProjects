<div class="cooked"><p>Lesson 9 Score Keeper app:</p>
<p><strong>Challenges:</strong></p>
<ul>
<li>contentDescritption and layout_width, height - get errors if not defined in xml. tried setting in the styles resource, but still get errors, also tried setting in other resource files and referencing the files, but still get errors if not specified in the main.xml</li>
<li>could not use some attributes due to API version 15 - some of the button views were limited</li>
<li>no drop shadows, very flat view - Base images are square but have transparent backgrounds so adding any type of border would show  the full square image.</li>
<li>no rotation as the rotated look and feel was clunky and having a different UI for landscape vs. Portrait seemed beyond the scope of the lesson</li>
<li>the way to create a Table and add background and lines on rows seemed clunky. I was looking for the equivalent of a boostrap for android. Decided to just go with the basics for now.</li>
<li>I get the warning, set baseline false for better performance, but this is not supported with API 15. Wondering why I get his warning when I have my API level set at 15. I noticed this with other things as well in that some attribute options come up with the type ahead but are not actually supported by the API version I’ve selected. It seems it would be better to not provide the attribute option in the first place.</li>
<li>I’d like to make the runPlay method more elegant but will save that for something in the future.</li>
</ul>
<p><strong>Rules of the Game, loosely based on Australian Rules Football</strong></p>
<p><em><strong>Teams can score three ways:</strong></em></p>
<ul>
<li>goal - 6pts - when ball goes thru tall goal posts of the opposing team</li>
<li>behind - 1pt - when ball goes thru short goal posts of the opposing team</li>
<li>rushed behind - 1pt - when opposing team sends ball thru short goal posts on their end of the field (kind of like a Safety in American Rules Football)</li>
</ul>
<p><strong><em>The way the app works:</em></strong></p>
<ol>
<li>on load, all values are reset and the “Run Play” is disabled</li>
<li>click on the “New Game” button to start a game:<br>
a. The play button is now clickable<br>
b. A football icon will appear next to the Active Team (default to the team on the left side of the screen)<br>
c. The number 5 appears between the team logos. this is the “Try” counter</li>
<li>The home team gets 5 Trys, then the Away team gets 5 Trys. The Active football icon will move accordingly</li>
<li>clicking the “Run Play” button will run a Try</li>
<li>If the play results in a goal, behind, or rushed behind, the scores and Stats table are updated accordingly</li>
<li>after each team runs 5 Tries, the game ends.<br>
a. the screen is updated to display "Game Over"<br>
b. the higher score is set to Bold, both are Bold if there is a tie</li>
</ol>
<p><strong><em>Underneath:</em></strong></p>
<ol>
<li>
<p>The app does NOT support rotating, should always use the Portrait layout view</p>
</li>
<li>
<p>the result of the play is determined by a random number generator:<br>
a. 1 = rushed behind, 3 = behind, 5 = goal</p>
</li>
<li>
<p>The final score is formatted as per the Aussie Rules,<br>
a. <span class="hashtag">#ofGoals</span>.<span class="hashtag">#ofBehinds</span>(TotalScore), for example<br>
Team A had 3 goals, 2 behinds, and 1 rushed behind<br>
Team B had 2 goals, 5 behinds, and 2 rushed behind</p>
<p>The final formatted score should be like:<br>
Team A would have 3.4(22) - this is because team A scored 3 goals, had 2 behinds and team B had 2 rushed beinds ((3*6) + 2 + 2 = 22)</p>
<p>Team B would have 2.6(18) - this is because team B scored 2 goals, had 5 behinds, and team A had 1 rushed behind ((2*6) + 5 + 1 = 18)</p>
</li>
</ol>
