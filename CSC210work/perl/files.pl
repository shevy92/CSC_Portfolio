#!/usr/bin/perl -w

if ($#ARGV != 0) {
 die "Please supply a file name as a command line arg!! \n"
}

$filename = $ARGV[0];

open (FILE_TO_WRITE, ">$filename");

print FILE_TO_WRITE ">Fasta sequence (its all mine!) \n";
print FILE_TO_WRITE "ACGTAGATCGATAGCTAGATCGATGAT\n";
print FILE_TO_WRITE "AGCTAGATAGCTAGATCGATAGAT\n";
print FILE_TO_WRITE "AGCATAGATATAATCCCCAGAGCTAGAGAATAGCTA\n";

close (FILE_TO_WRITE);

open (FILE_TO_READ, "$filename");

$seq = "";

while ($line = <FILE_TO_READ>) {
  if ($line !~ /^>/) {
    chomp($line);
    $seq = $seq.$line;
  }
}

print "Sequence: $seq\n";

