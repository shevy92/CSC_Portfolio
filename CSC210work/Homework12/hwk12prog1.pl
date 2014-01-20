#!/usr/bin/perl -w
#Sevika Singh

$growthRate = 1.94;
#Get growth rate
print "Enter a growth rate: ";
  $growthRate = <STDIN>;
print "Enter an initial population: ";
  $initialPopulation = <STDIN>;
print "Enter number of years: ";
  $years = <STDIN>;

$growthRatePercent = $growthRate;

#Calculate and Print
$populationSize = calcPop($initialPopulation, $years, $growthRate);
print "The population after $years year(s) at a rate of $growthRatePercent % annually is: $populationSize \n";

#Subroutine to Calculate
sub calcPop {
$finalPop = $initialPopulation;
$growthRate = $growthRate / 100;
  for ($i=1; $i <= $years; $i = $i + 1) {
    $finalPop = ($finalPop * $growthRate) + $finalPop;
  }
return $finalPop;
}

