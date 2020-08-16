package com.api.Blogged;

import com.api.Blogged.util.HashUtil;

public class Test {

	public static void main(String[] args) {
		try {
			System.out.println(HashUtil.getSaltedHash("RanaDey"));
			if (HashUtil.check("a",
					"N/0aA/1GspWkSKv6CCkO6JnGxaKVmiwF+Af7J2C3uYA=$KEfcnP2O7RXnJZnJZ5BXs84Y+Ok3Uj8Uja0M6Tk4MJg="))
				System.out.println("matched");
			else
				System.out.println("Not matched");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
