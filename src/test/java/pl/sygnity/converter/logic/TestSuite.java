package pl.sygnity.converter.logic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestConverter.class,
	TestDatabase.class,
	TestNbpApiHandler.class
})
public class TestSuite {
	
}