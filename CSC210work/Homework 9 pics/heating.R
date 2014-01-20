#heating of a probe

#time array
x = c(0,2,4,6,8,10,12,14,16,18,20)

#temp array
y = c(32.77,33.16,33.35,33.56,33.67,33.75,33.85,33.93,33.98,34.03,34.04)

#temp2 array
y2 = c(32.77,33.16,33.35,33.56,33.67,33.75,33.85,33.93,33.98,34.03)

plot(x,y)
par(ask=TRUE)

rate = 0

#print (rate)
for(i in 1:length(y)-1){
  rate[i] = (y[i+1] - y[i]) / 2
}

#linear least squares fit
#drop last y entry
 
plot(y2, rate, col="red", main="Least square fit to rates")
fit = lm(rate~y2)
abline(fit)
print(fit)
print(summary(fit)) #lots more info
