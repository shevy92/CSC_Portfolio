#!/usr/bin/perl -w
#Sevika Singh

$DNAsequence = "GACGTCGCCAGAGAggcataTAACGATAtgacacagagagagcaGAGACAAGT";

$DNAsequence = uc($DNAsequence);

$whereFirst = index($DNAsequence, "AGAG");
$whereLast = rindex($DNAsequence, "AGAG");

$whereFirst = $whereFirst + 4;
$number = $whereLast - $whereFirst;

print $number, "\n";



