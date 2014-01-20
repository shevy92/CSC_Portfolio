#!/usr/bin/perl -w
# Sevika Singh

# Declarations
$array[0] = 0;
$array[1] = 1;

# Store Values
$a = 0;
$b = 1;
$x = 2;
  for ($x; $x < 20; $x = $x + 1) {
    $array[$x] = $array[$a] + $array[$b];
    $a = $a +1;
    $b = $b +1;
  }

# Print Values
print "     Fibonacci Numbers \n";
  for ($y=0; $y < 20; $y = $y +1) {
    print $y+1, ": \t", $array[$y], "\n";
  }
