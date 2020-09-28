## TicTacToe
TicTacToe based on `Android` and `Kotlin`

### Background
`TicTacToe` is a game where the objective is to make 3 X’s or 3 O’s in a `horizontal`, `diagonal`, or `vertical` line on a 3x3 board. 
If you have never heard of it simply google ‘Tic Tac Toe’ and play a few games to understand how it works.

### Dependencies
```bash
    implementation 'com.github.zcweng:switch-button:0.0.3@aar'  
    implementation 'com.github.bumptech.glide:glide:4.11.0'  
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'  
    implementation fileTree(dir: "libs", include: ["*.jar"])  
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"  
    implementation 'androidx.core:core-ktx:1.3.1'  
    implementation 'androidx.appcompat:appcompat:1.2.0'  
    implementation 'com.google.android.material:material:1.2.1'  
    implementation 'androidx.constraintlayout:constraintlayout:2.0.1'  
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.0'  
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.0'  
    implementation 'androidx.preference:preference:1.1.0'  
    testImplementation 'junit:junit:4.12'  
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'  
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'  
```

### Main Features
**1.Basic functionality**  
When the app is opened by the user, a 3x3 grid is presented to the user. When the 
user taps any of the squares, that square will be marked with an X. the next tap (on a different square will mark 
it with an O, and so on. 

* **X win**  
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe1.gif?raw=true" width="60%" height="60%"></div>  
</br>

* **O win**  
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe2.gif?raw=true" width="60%" height="60%"></div>  
</br>

* **Tie**  
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe3.gif?raw=true" width="60%" height="60%"></div>  
</br>

**2.Check invalid move**  
A user taps on a square that is already claimed
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe4.gif?raw=true" width="60%" height="60%"></div>  
</br>

**3.Check invalid click**  
When a game is over, show a toast to users if any block is clicked.
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe9.gif?raw=true" width="60%" height="60%"></div>  
</br>


**4.Setting page**  
A toolbar at the top with a dropdown menu that allows the user to enter a separate ‘Settings’ screen. 
The settings screen will be a separate fragment (or activity) which will have a single ‘Switch’ widget
called ‘auto-reset’, which when toggled allows the game to automatically reset once the game has 
completed (win or tie). If auto-reset switch is not toggled, the game will wait for user to press reset 
button (below).
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe5.gif?raw=true" width="60%" height="60%"></div>  
</br>

* **Auto-reset on**  
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe6.gif?raw=true" width="60%" height="60%"></div>  
</br>

* **Auto-reset off**   
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe1.gif?raw=true" width="60%" height="60%"></div>  
</br>

**5.Handles device rotation and back button press**  
When the device is rotated into landscape mode, or 
the back button is pressed, MainActivity’s onDestroy() method is called, destroying the activity. Then, onCreate() 
is called and the activity recommences in the landscape mode. Unless handled properly, this will reset the 
game’s progress (bad).
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe7.gif?raw=true" width="60%" height="60%"></div>  
</br>
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe8.gif?raw=true" width="60%" height="60%"></div>  
</br>

**6.Clear scores**  
When `Clear` button is clicked, reset the scores.  
<div align=center><img src="https://github.com/Grindewald1900/TicTacToe/blob/master/Images/TicTacToe10.gif?raw=true" width="60%" height="60%"></div>  
</br>
