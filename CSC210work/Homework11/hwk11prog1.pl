#!/usr/bin/perl -w
# Sevika Singh

# DNA sequences
  #Test1: TATACCCATGCCCT;
  #Test2: CCACATCATATCTATAGGCAT;
  #Test3: TACGCCCGTCAATG;
    $string = "TACGCCCGTCAATG";
    $string2 = reverse($string);
    $string2 =~ tr/ACGT/TGCA/;  

    $pattern = "TATAxxxATGxxxT";

$exit = 0;
$index = 0;

# Identify if TATA
  while (!(substr($string,$index,4) eq "TATA") && ($index <= length($string)-length($pattern))) {
    $index = $index + 1;
  }

$patternPos = substr($string, $index, 14);

# Identify for rest
  if (index($patternPos, "ATG") == 7) {
    $exit = 0;
  } elsif (rindex($patternPos, "T") == 13) {
    $exit = 0;
  } else {
    $exit = 1;
  }

# Try Reverse
  if ($exit == 1) {
    	
$exit = 0;
$index2 = 0;

# Identify if TATA
  while (!(substr($string2,$index2,4) eq "TATA") && ($index2 <= length($string2)-length($pattern))) {
    $index2 = $index2 + 1;
  }

$patternPos = substr($string2, $index2, 14);

# Identify for rest
  if (index($patternPos, "ATG") == 7) {
    $exit = 2;
  } elsif (rindex($patternPos, "T") == 13) {
    $exit = 2;
  } else {
    $exit = 1;
  }

}

# Print Result
  if ($exit == 0) {
    print "The pattern was found at index $index! \n";
  }

  if ($exit == 2) {
    $value = length ($string2) - $index2 -1;
    print "The pattern was found in the reverse complement at index $value!\n";
  }

  if ($exit == 1) {
    print "The pattern was not found \n";
  }
