/**
 * Copyright (c) 2007-2013 BEORN Technologies, SARL. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package com.beorn.onlinepayment.messaging;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class MessageBroker {

	public void start() throws Exception {
		_log.info("Starting message broker");

		_broker = new BrokerService();
		_broker.setBrokerName("btop");
		_broker.setDataDirectory("amq/data");
		//_broker.setTmpDataDirectory("amq/tmp");
		_broker.setSchedulerDirectory("amq/scheduler");
		_broker.addConnector(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
		_broker.start();
	}

	public void stop() throws Exception {
		_broker.stop();
	}

	private BrokerService _broker;

	private static Log _log = LogFactoryUtil.getLog(MessageBroker.class);
}
