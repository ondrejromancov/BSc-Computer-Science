import random
import math
import csv
import matplotlib.pyplot as plt
import numpy as np

def nextTime(mean):
    return -mean * math.log(1 - random.random())

def theoreticalMeanQueueLength(alpha, beta):
    """
    >>> theoreticalMeanQueueLength(10, 2)
    0.25
    >>> theoreticalMeanQueueLength(5, 1)
    0.25
    >>> theoreticalMeanQueueLength(4, 2)
    1.0
    >>> theoreticalMeanQueueLength(5.5, 1.3)
    0.3095238095238095
    >>> theoreticalMeanQueueLength(5.5, 0)
    0.0
    >>> theoreticalMeanQueueLength(1, 1)
    -1
    >>> type(theoreticalMeanQueueLength(10, 2))
    <class 'float'>
    """
    
    fraction = beta/alpha

    if fraction == 1:
    	return -1
    else:
    	return float(fraction/(1-fraction))

def checkMean(mean, iterations=10000):
    """
    >>> random.seed(57)
    >>> checkMean(5, 10)
    6.309113224728108
    >>> random.seed(57)
    >>> checkMean(5, 1000)
    4.973347344130324
    >>> random.seed(57)
    >>> checkMean(5, 100000)
    4.988076126529703
    >>> random.seed(57)
    >>> checkMean(195, 100000)
    194.53496893466047
    >>> random.seed(57)
    >>> checkMean(195)
    196.71853828860912
    >>> random.seed(57)
    >>> checkMean(31)
    31.273203522804728
    >>> type(checkMean(31, 5))
    <class 'float'>
    """
    actualmean = 0
    for n in range(0, iterations):
    	actualmean = actualmean + nextTime(mean)
    return float(actualmean/iterations)

def readExperimentParameters(filename):
    """
    >>> readExperimentParameters('experiments.csv')[0]
    (10, 2, 480)
    >>> len(readExperimentParameters('experiments.csv'))
    5
    >>> readExperimentParameters('experiments.csv')[3]
    (20, 2, 480)
    >>> readExperimentParameters('experiments.csv')[2]
    (20, 15, 240)
    >>> type(readExperimentParameters('experiments.csv')[1])
    <class 'tuple'>
    """

    values = []
    rowdata = []

    with open(filename) as csvfile:
    	rdr = csv.reader(csvfile)
    	next(rdr)
    	for row in rdr:
    		rowdata.append(int(row[0]))
    		rowdata.append(int(row[1]))
    		if str(row[3]) == " h":
    			rowdata.append(int(row[2])*60)
    		else:
    			rowdata.append(int(row[2]))
    		values.append(tuple(rowdata))
    		rowdata.clear()
    	return values	

#Algorithms for the different scenarious are below:

def singleQueueMax(alpha, beta, time=480):
    """
    >>> random.seed(57)
    >>> singleQueue(10, 3, 480)
    3
    >>> random.seed(101)
    >>> singleQueue(5, 3, 480)
    6
    >>> random.seed(101)
    >>> singleQueue(5, 3)
    6
    >>> random.seed(935)
    >>> singleQueue(10, 9, 280)
    10
    >>> type(singleQueue(10, 9, 280))
    <class 'int'>
    """
    
	#This method returs the MAXIMUM length of the queue throughout the time of the simulation

    #Initialise the variables
    ta = 0
    ts = 0
    maxq = 0
    c = 0
    Q = 1
    simTime = time

    #Main loop of the algorithm
    while (c<simTime): 
    	if ta < ts:
    		ts = ts - ta
    		c = c + ta
    		Q = Q + 1
    		if Q > maxq: #update maxQ
    			maxq = Q
    		ta = nextTime(alpha)
    	else:
    		ta = ta - ts
    		c = c + ts
    		Q = Q - 1 
    		ts = nextTime(beta)
    	if Q == 0:
    		c = c + ta
    		Q = Q + 1
    		if Q > maxq: #update maxQ
    			maxq = Q
    		ta = nextTime(alpha)
    	else:
    		continue
    else:
    	return maxq

def singleQueueAverage(alpha, beta, time=480):
	#This method returs the mean length of the queue throughout the time of the simulation

    #Initialise the variables
    ta = 0
    ts = 0
    totalq = 0
    count = 0
    c = 0
    Q = 1
    simTime = time

    #Main loop of the algorithm
    while (c<simTime):
    	totalq += Q #add current length of queue to the total queue length
    	count += 1 #increment count
    	if ta < ts:
    		ts = ts - ta
    		c = c + ta
    		Q = Q + 1
    		ta = nextTime(alpha)
    	else:
    		ta = ta - ts
    		c = c + ts
    		Q = Q - 1 
    		ts = nextTime(beta)
    	if Q == 0:
    		c = c + ta
    		Q = Q + 1
    		ta = nextTime(alpha)
    	else:
    		continue
    else:
    	return (totalq/count) #return average queue length

def doubleQueueMax(alpha,beta, time=480):
	#This method returns the maximum length of any of the two queues throughout the time of the simulation

	#initialise the variables
    ta = 0
    ts1 = 0 #time for the customer to be served at the first counter
    ts2 = 0 #time for the customer to be served at the second counter
    maxq = 0
    c = 0
    Q1 = 1 #current length of queue one
    Q2 = 1 #current length of queue two
    simTime = time

    while (c<simTime):
    	#Branch for the next critical event being customer served at counter 1 and also initial customer being served
    	if ((ts1<ts2) and (ts1<ta)) or ((ts1==ts2) and (ts1==0) and (ts1<ta)):
    		ta -= ts1
    		ts2 -= ts1
    		c += ts1
    		Q1 -= 1 #person leaving queue 1
    		ts1 = nextTime(beta)
    	#Branch for the next critical event being customer served at counter 2
    	elif (ts2<ts1) and (ts2<ta):
    		ta -= ts2
    		ts1 -= ts2
    		c += ts2
    		Q2 -= 1 #person leaving queue 2
    		ts2 = nextTime(beta)
    	#Branch for the next critical event being customer arriving and joining the shortest queue
    	else:
    		ts1 -= ta
    		ts2 -= ta
    		c += ta
    		if Q1>Q2: #determining the shortesr queue
    			Q2 += 1 #person joining shorter queue 2
    		else:
    			Q1 += 1 #person joining shorter queue 1
    		if Q1 > maxq or Q2 > maxq: #updating maxQ if one of the queus is larger than current maxQ
    			maxq = max(Q1,Q2)
    		ta = nextTime(alpha)
    	if Q1 == 0:
    		c += ta
    		Q1 += 1
    		ta = nextTime(alpha)
    	elif Q2 == 0:
    		c += ta
    		Q2 += 1
    		ta = nextTime(alpha)
    	else:
    		continue
    else:
    	return maxq

def doubleQueueAverage(alpha,beta, time=480):
	#This method returs the mean length of both of the queues throughout the time of the simulation

	#initialise the variables
    ta = 0
    ts1 = 0
    ts2 = 0
    totalq = 0
    count = 0
    c = 0
    Q1 = 1
    Q2 = 1
    simTime = time

    while (c<simTime):
    	totalq += Q1 #adding the current length of queue 1
    	totalq += Q2 #adding the current length of queue 2
    	count += 1 #incrementing count
    	if ((ts1<ts2) and (ts1<ta)) or ((ts1==ts2) and (ts1==0) and (ts1<ta)):
    		ta -= ts1
    		ts2 -= ts1
    		c += ts1
    		Q1 -= 1
    		ts1 = nextTime(beta)
    	elif (ts2<ts1) and (ts2<ta):
    		ta -= ts2
    		ts1 -= ts2
    		c += ts2
    		Q2 -= 1
    		ts2 = nextTime(beta)
    	else:
    		ts1 -= ta
    		ts2 -= ta
    		c += ta
    		if Q1>Q2: #determining the shortesr queue
    			Q2 += 1 #person joining shorter queue 2
    		else:
    			Q1 += 1 #person joining shorter queue 1
    		ta = nextTime(alpha)
    	if Q1 == 0:
    		count += 1 #incrementing count 
    		c += ta
    		Q1 += 1
    		ta = nextTime(alpha)
    	elif Q2 == 0:
    		count += 1 #incrementing count 
    		c += ta
    		Q2 += 1
    		ta = nextTime(alpha)
    	else:
    		continue
    else:
    	return (totalq/(2*count)) #dividing the total queue length by the number of times the queue changed times by two (two queues)

#Functions running simulations and displaying graphs are below:

def runSimulation():
	'''
	This function plots a graph displaying theoretical mean queue length with
	changes in alpha
	'''

	meanque = [] #list for the theoretical mean queue values 
	alphavalues = np.arange(1.1,10.1,0.1) #range of values of alpha
	for n in alphavalues:
		meanque.append(theoreticalMeanQueueLength(n,1))

	#plotting the graph
	plt.plot(alphavalues, meanque)
	plt.ylabel('Theoretical Mean Queue Length')
	plt.xlabel('Alpha values')
	plt.title('Theoretical Mean Queue Length against Alpha')
	plt.show()

def runSimulationReducedBetaMax():
	'''
	This function plots a graph displaying the effects of changes in alpha and
	beta on the AVERAGE MAXIMUM queue length which is calculated from 1000 iterations
	of singleQueueMax() algorithm
	'''

	alphavalues = np.arange(1.1,10.1,0.1) #range of values of alpha
	betavalues = np.arange(1,0,-0.1) #range of values of beta

	for m in betavalues:
		meanque = []
		for n in alphavalues:
			maxqueue = 0
			for o in range(1000):
				maxqueue = maxqueue + singleQueueMax(n,m)
			meanque.append(maxqueue/1000)
		plt.plot(alphavalues, meanque, label="Beta = %d"%(m*100,)+" percent")

	#plotting the graph
	plt.ylabel('Average Maximum Queue Length over 1000 iterations')
	plt.xlabel('Alpha values')
	plt.title('The effect of reduction of beta on AVERAGE MAXIMUM queue length')

	#adding legend
	legendbox = plt.legend(loc='best',ncol=1,fancybox=True)
	legendbox.get_frame().set_alpha(0.5)

	plt.show()

def runSimulationReducedBetaAverage():
	'''
	This function plots a graph displaying the effects of changes in alpha and
	beta on the AVERAGE MEAN queue length which is calculated from 1000 iterations
	of singleQueueAverage() algorithm
	'''
	
	alphavalues = np.arange(1.1,10.1,0.1) #range of values of alpha
	betavalues = np.arange(1,0,-0.1) #range of values of alpha

	for m in betavalues:
		meanque = []
		for n in alphavalues:
			maxqueue = 0
			for o in range(1000):
				maxqueue = maxqueue + singleQueueAverage(n,m)
			meanque.append(maxqueue/1000)
		plt.plot(alphavalues, meanque, label="Beta = %d"%(m*100,)+" percent")

	#plotting the graph
	plt.ylabel('Average Mean Queue Length over 1000 iterations')
	plt.xlabel('Alpha values')
	plt.title('The effect of reduction of beta on AVERAGE MEAN queue length')

	#adding legend
	legendbox = plt.legend(loc='best',ncol=1,fancybox=True)
	legendbox.get_frame().set_alpha(0.5)

	plt.show()

def runSimulationSecondTellerMax():
	'''
	This function plots a graph displaying the effects of changes in alpha on the AVERAGE MAXIMUM
	queue length of the two queues which is calculated from 1000 iterations of doubleQueueMax()
	algorithm

	It also plots several singleQueueMax curves with beta reduction for comparison
	'''
	
	alphavalues = np.arange(1.1,10.1,0.1) #range of values of alpha
	betavalues = np.arange(0.5,0,-0.1) #range of values of beta

	meanque = []
	for n in alphavalues:
		maxqueue = 0
		for o in range(1000):
			maxqueue = maxqueue + doubleQueueMax(n,1)
		meanque.append(maxqueue/1000)
	plt.plot(alphavalues, meanque, label="Two tellers with two queues")

	for m in betavalues:
		meanque = []
		for n in alphavalues:
			maxqueue = 0
			for o in range(1000):
				maxqueue = maxqueue + singleQueueMax(n,m)
			meanque.append(maxqueue/1000)
		plt.plot(alphavalues, meanque, label="Beta = %d"%(m*100,)+" percent")

	#plotting the graph
	plt.ylabel('Average Maximum Queue Length over 1000 iterations')
	plt.xlabel('Alpha values')
	plt.title('Comparison of Customer service benefits (Worst Case)')

	#adding legend
	legendbox = plt.legend(loc='best',ncol=1,fancybox=True)
	legendbox.get_frame().set_alpha(0.5)

	plt.show()

def runSimulationSecondTellerAverage():
	'''
	This function plots a graph displaying the effects of changes in alpha on the AVERAGE MEAN
	queue length of the two queues which is calculated from 1000 iterations of doubleQueueAverage()
	algorithm

	It also plots several singleQueueAverage curves with beta reduction for comparison
	'''
	
	alphavalues = np.arange(1.1,10.1,0.1) #range of values of alpha
	betavalues = np.arange(0.5,0,-0.1) #range of values of beta

	meanque = []
	for n in alphavalues:
		maxqueue = 0
		for o in range(1000):
			maxqueue = maxqueue + doubleQueueAverage(n,1)
		meanque.append(maxqueue/1000)
	plt.plot(alphavalues, meanque, label="Two tellers with two queues")

	for m in betavalues:
		meanque = []
		for n in alphavalues:
			maxqueue = 0
			for o in range(1000):
				maxqueue = maxqueue + singleQueueMax(n,m)
			meanque.append(maxqueue/1000)
		plt.plot(alphavalues, meanque, label="Beta = %d"%(m*100,)+" percent")

	#plotting the graph
	plt.ylabel('Average Mean Queue Length over 1000 iterations')
	plt.xlabel('Alpha values')
	plt.title('Comparison of Customer service benefits (Average)')

	#adding legend
	legendbox = plt.legend(loc='best',ncol=1,fancybox=True)
	legendbox.get_frame().set_alpha(0.5)

	plt.show()





