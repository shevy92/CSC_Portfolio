#!/usr/bin/perl -w

use strict;

my $k = 100;
my $j; 

for (my $i = 1; $i <10; $i = $i +1) {
  my $k = 20;
  $j = $j + $i;
}

print "$j\n";
print "$k\n";
