/*
 * MIT License
 *
 * Copyright (c) 2020 OpeningO Co.,Ltd.
 *
 *    https://openingo.org
 *    contactus(at)openingo.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.openingo.jdkits.http;

import org.openingo.jdkits.validate.ValidateKit;

import java.util.HashMap;

/**
 * RespData with status code, status message and data.
 * status code using Strings recommends.
 *
 * @author Qicz
 */
public final class RespData extends HashMap<String, Object> {

	private static final long serialVersionUID = 7348797463704162367L;
	
	private static final String REQUEST_DATA_INVALID = "request_data_invalid!";
	private static final String RESPONSE_DATA_INVALID = "response_data_invalid!";

	/**
	 * invalid request
	 */
	public static RespData invalidReq() {
		return RespData.failure(REQUEST_DATA_INVALID);
	}
	
	/**
	 * invalid response
	 */
	public static RespData invalidResp() {
		return RespData.failure(RESPONSE_DATA_INVALID);
	}
	
	/**
	 * Response result
	 */
	public static RespData ret(RespData data) {
		if (ValidateKit.isNull(data)) {
			return RespData.invalidResp();
		}
		return data;
	}
	
	/**
	 * Success Response
	 * @return response with success status
	 */
	public static RespData success() {
		return (new RespData(Config.SUCCESS_SC, Config.SUCCESS_SM));
	}

	/**
	 * Success Response
	 * @return response data with success status
	 */
	public static RespData success(Object data) {
		return (new RespData(Config.SUCCESS_SC, Config.SUCCESS_SM, data));
	}
	
	/**
	 * Failure Response
	 * @param sm response status message
	 * @return response with failure status
	 */
	public static RespData failure(String sm) {
		return (new RespData(Config.FAILURE_SC, sm));
	}

	private RespData() {
		
	}
	
	private RespData(Object sc, String sm) {
		if (!Config.SM_ONLY) {
			this.put(Config.SC_KEY, sc);
		}
		this.put(Config.SM_KEY, sm);
	}
	
	private RespData(Object sc, String sm, Object data) {
		this(sc, sm);
		if (ValidateKit.isNotNull(data)) {
			this.put(Config.DATA_KEY, data);
		}
	}

	/**
	 * Get Current Request's response status code
	 * @return response status code, <tt>'unset'</tt> if {@linkplain Config#SM_ONLY} is true
	 */
	public Object getSc() {
		if (Config.SM_ONLY) {
			return Config.UNSET_SC;
		}
		return this.get(Config.SC_KEY).toString();
	}

	/**
	 * Get Current Request's response status message
	 * @return response status message
	 */
	public String getSm() {
		return this.get(Config.SM_KEY).toString();
	}

	/**
	 * Get Response data
	 */
	@SuppressWarnings("unchecked")
	public <T> T getData() {
		if (!this.containsKey(Config.DATA_KEY)) {
			return null;
		}
		return (T)this.get(Config.DATA_KEY);
	}

	public final static class Config {
		/**
		 * Status code key, default 'sc'
		 */
		public static String SC_KEY = "sc";

		/**
		 * Status message key, default 'sm'
		 */
		public static String SM_KEY = "sm";

		/**
		 * Response data key, default 'data'
		 */
		public static String DATA_KEY = "data";

		/**
		 * success status code, default is 'success'.
		 */
		public static Object SUCCESS_SC = "success";

		/**
		 * success status code, default is 'response successful'.
		 */
		public static String SUCCESS_SM = "response successful";

		/**
		 * failure status code, default is 'failure'.
		 */
		public static Object FAILURE_SC = "failure";

		/**
		 * only contains status message
		 */
		public static boolean SM_ONLY = false;

		/**
		 * 'unset' status code
		 */
		public static final Object UNSET_SC = "unset";

		/**
		 * friendly failure message, default "The system is temporarily unavailable"
		 */
		public static String FRIENDLY_FAILURE_MESSAGE = "The system is temporarily unavailable";
	}
}
