#!/usr/bin/perl -w
#Sevika Singh

$DNASequence = "GACGTCGCCAGAGAGACG";

$codon1 = substr($DNASequence, 0, 3);
$codon2 = substr($DNASequence, 3, 3);
$codon3 = substr($DNASequence, 6, 3);
$codon4 = substr($DNASequence, 9, 3);
$codon5 = substr($DNASequence, 12, 3);
$codon6 = substr($DNASequence, 15, 3);

@codonArray = ($codon1, $codon2, $codon3, $codon4, $codon5, $codon6);

print "$DNASequence\n";
print $codonArray[0], "\t", $codonArray[1], "\t", $codonArray[2], "\t",
        $codonArray[3], "\t", $codonArray[4], "\t", $codonArray[5], "\t\n";


