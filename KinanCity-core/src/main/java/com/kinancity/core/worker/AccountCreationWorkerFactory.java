package com.kinancity.core.worker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

import com.kinancity.core.captcha.CaptchaQueue;
import com.kinancity.core.proxy.ProxyManager;
import com.kinancity.core.scheduling.AccountCreationQueue;
import com.kinancity.core.throttle.Bottleneck;
import com.kinancity.core.worker.callbacks.CreationCallbacks;

import lombok.Getter;

public class AccountCreationWorkerFactory {

	@Getter
	private List<String> trainerNames = Arrays.asList("Ash", "Misty", "Brock", "Tracey", "May", "Max", "Dawn", "Iris", "Cilan", "Serena", "Clemont", "Bonnie", "Lana", "Mallow", "Lillie", "Sophocles", "Kiawe");

	private int position = -1;

	private String getNextName() {
		if (position < 0) {
			position = RandomUtils.nextInt(trainerNames.size());
			Collections.shuffle(trainerNames);
		}
		position = position % trainerNames.size();

		return trainerNames.get(position++);
	}

	public AccountCreationWorker createWorker(AccountCreationQueue accountCreationQueue, CaptchaQueue captchaQueue, ProxyManager proxyManager, CreationCallbacks callbacks, Bottleneck bottleneck) {
		return new AccountCreationWorker(accountCreationQueue, getNextName(), captchaQueue, proxyManager, callbacks, bottleneck);
	}

}
