#!/usr/bin/perl -w
#Sevika Singh


#Get the FASTA sequence and process it to extract the DNA string.
$seq = "";
  while ($line = <STDIN>) {
    if ($line !~ /^>/) {
      chomp($line);
      $seq .= $line;
    }
  }

#Create an array to store the counters, which will allow 
#to count the occurences of the patterns, separated by x characters, 
#with x ranging from 0 to 20.
@counters;
  for ($i = 0; $i <= 20; $i = $i +1) {
    $counters[$i] = 0;
  }

#Search in the DNA string to find AGGAGG and ATG separated 
#by 0-20 chars. Repeat
  while ($seq =~ /(AGGAGG.{0,20}ATG)/g) {
    $counters[length($1) - 9] = $counters[length($1) - 9] + 1;
  }

#Print counters
  print "Forward strands:\n";
  for ($i = 0; $i <=20; $i = $i +1) {
    print "For gap size $i, the number of pattern occurences is ";
    print "$counters[$i] \n";
  }


#Repeat for complementary strands
$seq2 = $seq;
$seq2 = reverse($seq2);
$seq2 =~ tr/ACGT/TGCA/;

#Create an array to store the counters, which will allow
#to count the occurences of the patterns, separated by x characters,
#with x ranging from 0 to 20.
@counters2;
  for ($i = 0; $i <= 20; $i = $i +1) {
    $counters2[$i] = 0;
  }

#Search in the DNA string to find AGGAGG and ATG separated
#by 0-20 chars. Repeat
  while ($seq2 =~ /(AGGAGG.{0,20}ATG)/g) {
    $counters2[length($1) - 9] = $counters2[length($1) - 9] + 1;
  }

#Print counters
  print "Reverse Complementary strands:\n";
  for ($i = 0; $i <=20; $i = $i +1) {
    print "For gap size $i, the number of pattern occurences is ";
    print "$counters2[$i] \n";
  }
