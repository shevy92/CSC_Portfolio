#flour beetle data

#daily count of beetles for 16 days
y = c(112, 152, 212, 258, 306, 309, 315, 310, 
298, 290, 303, 295, 311, 308, 299, 309)
print(y)

#daily count of beetles for 15 days
y1 = c(112, 152, 212, 258, 306, 309, 315, 310, 
298, 290, 303, 295, 311, 308, 299)

#time in days 
x = seq(0, length(y)-1)
print(x)

plot(x, y, main="Daily beetle counts")

par(ask=TRUE)  #to pause between plots

#create a rate vector and load it with computed rates
rate = 0
#print (rate)

for (i in 1:length(y)-1){
rate[i] = (y[i+1] - y[i])/y[i]
}
print(rate)

#linear least squares fit
#drop last y entry
 
plot(y1, rate, col="red", main="Least square fit to rates")
fit = lm(rate~y1)
abline(fit)
print(fit)
print(summary(fit)) #lots more info

